package SpringBoot.Test.service;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.Converter.TrangThaiDonConverter;
import SpringBoot.Test.payload.DataRequest.ThemDonDangKyRequest;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiDonRequest;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.DaoTrangRepo;
import SpringBoot.Test.repository.DonDangKyRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.TrangThaiDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TrangThaiDonService implements ITrangThaiDon{
    @Autowired
    private DonDangKyRepo donDangKyRepo;
    @Autowired
    private TrangThaiDonRepo trangThaiDonRepo;
    @Autowired
    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;

    private final TrangThaiDonConverter _converter;
    private final ResponseObject<TrangThaiDonDTO> _responseObject;
    public TrangThaiDonService(TrangThaiDonConverter converter, ResponseObject<TrangThaiDonDTO> responseObject){
        _converter = converter;
        _responseObject = responseObject;
    }

    private Set<DonDangKy> themListDonDangKy(int trangThaiDonId, List<ThemDonDangKyRequest> requests) {
        var trangThaiDon = trangThaiDonRepo.findById(trangThaiDonId);
        if (trangThaiDon.isEmpty()) {
            return null;
        }
        Set<DonDangKy> set = new HashSet<>();
        for (var request: requests) {
            DonDangKy donDangKy = new DonDangKy();
            donDangKy.setTrangThaiDon(trangThaiDon.get());
            donDangKy.setNgayGuiDon(request.getNgayGuiDon());
            donDangKy.setNgayXuLy(request.getNgayXuLy());
            donDangKy.setNguoiXuLy(request.getNguoiXuLy());
            var daoTrang = daoTrangRepo.findById(request.getDaoTrangId()).get();
            donDangKy.setDaoTrang(daoTrang);
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            donDangKy.setPhatTu(phatTu);
            set.add(donDangKy);
        }
        donDangKyRepo.saveAll(set);
        trangThaiDon.get().setDonDangKys(set);
        trangThaiDonRepo.save(trangThaiDon.get());
        return set;
    }

    @Override
    public ResponseObject<TrangThaiDonDTO> themTrangThaiDonRequest(ThemTrangThaiDonRequest request) {
        TrangThaiDon trangThaiDon = new TrangThaiDon();
        trangThaiDon.setTenTrangThai(request.getTenTrangThai());
        trangThaiDon.setDonDangKys(null);
        trangThaiDonRepo.save(trangThaiDon);
        trangThaiDon.setDonDangKys(themListDonDangKy(trangThaiDon.getId(), request.getDonDangKys()));
        return _responseObject.responseSuccess("Them trang thai don thanh cong", _converter.entityToDTO(trangThaiDon));
    }

    @Override
    public TrangThaiDon suaTrangThaiDon(TrangThaiDon trangThaiDonSua) {
        Optional<TrangThaiDon> trangThaiDonOptional = trangThaiDonRepo.findById(trangThaiDonSua.getId());
        if (trangThaiDonOptional.isEmpty()) {
            return null;
        }
        TrangThaiDon trangThaiDon = trangThaiDonOptional.get();
        trangThaiDon.setTenTrangThai(trangThaiDonSua.getTenTrangThai());
        trangThaiDon.setDonDangKys(trangThaiDonSua.getDonDangKys());
        trangThaiDonRepo.save(trangThaiDon);
        return trangThaiDon;
    }
}
