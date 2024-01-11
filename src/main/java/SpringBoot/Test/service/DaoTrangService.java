package SpringBoot.Test.service;

import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import SpringBoot.Test.payload.Converter.DaoTrangConverter;
import SpringBoot.Test.payload.Converter.QuyenHanConverter;
import SpringBoot.Test.payload.DataRequest.ThemDaoTrangRequest;
import SpringBoot.Test.payload.DataRequest.ThemDonDangKyRequest;
import SpringBoot.Test.payload.DataRequest.ThemPhatTuDaoTrangRequest;
import SpringBoot.Test.payload.DataRequest.ThemPhatTuRequest;
import SpringBoot.Test.payload.DataResponse.DaoTrangDTO;
import SpringBoot.Test.payload.DataResponse.DonDangKyDTO;
import SpringBoot.Test.payload.DataResponse.QuyenHanDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class    DaoTrangService implements IDaoTrang{
    @Autowired
    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;
    @Autowired
    private DonDangKyRepo donDangKyRepo;
    @Autowired
    private TrangThaiDonRepo trangThaiDonRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    private final DaoTrangConverter _converter;
    private final ResponseObject<DaoTrangDTO> _responseObject;
    public DaoTrangService(DaoTrangConverter converter, ResponseObject<DaoTrangDTO> responseObject){
        _converter = converter;
        _responseObject = responseObject;
    }
    private Set<DonDangKy> themListDonDangKy(int daoTrangId, List<ThemDonDangKyRequest> requests) {
        var daoTrang = daoTrangRepo.findById(daoTrangId);
        if (daoTrang.isEmpty()) {
            return null;
        }
        Set<DonDangKy> set = new HashSet<>();
        for (var request: requests) {
            DonDangKy donDangKy = new DonDangKy();
            donDangKy.setDaoTrang(daoTrang.get());
            donDangKy.setNgayGuiDon(request.getNgayGuiDon());
            donDangKy.setNgayXuLy(request.getNgayXuLy());
            donDangKy.setNguoiXuLy(request.getNguoiXuLy());
            var trangThaiDon = trangThaiDonRepo.findById(request.getTrangThaiDonId()).get();
            donDangKy.setTrangThaiDon(trangThaiDon);
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            donDangKy.setPhatTu(phatTu);
            set.add(donDangKy);
        }
        donDangKyRepo.saveAll(set);
        daoTrang.get().setDonDangKys(set);
        daoTrangRepo.save(daoTrang.get());
        return set;
    }
    private Set<PhatTuDaoTrang> themListPhatTuDaoTrang(int daoTrangId, List<ThemPhatTuDaoTrangRequest> requests) {
        var daoTrang = daoTrangRepo.findById(daoTrangId);
        if (daoTrang.isEmpty()) {
            return null;
        }
        Set<PhatTuDaoTrang> set = new HashSet<>();
        for (var request:requests) {
            PhatTuDaoTrang phatTuDaoTrang = new PhatTuDaoTrang();
            phatTuDaoTrang.setDaoTrang(daoTrang.get());
            phatTuDaoTrang.setDaThamGia(request.getDaThamGia());
            phatTuDaoTrang.setLyDoKhongThamGia(request.getLyDoKhongThamGia());
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            phatTuDaoTrang.setPhatTu(phatTu);
            set.add(phatTuDaoTrang);
        }
        phatTuDaoTrangRepo.saveAll(set);
        daoTrang.get().setPhatTuDaoTrangs(set);
        daoTrangRepo.save(daoTrang.get());
        return set;
    }
    @Override
    public ResponseObject<DaoTrangDTO> themDaoTrangRequest(ThemDaoTrangRequest request) {
        DaoTrang daoTrang = new DaoTrang();
        daoTrang.setDaKetThuc(request.getDaKetThuc());
        daoTrang.setNoiDung(request.getNoiDung());
        daoTrang.setNoiToChuc(request.getNoiToChuc());
        daoTrang.setSoThanhVienThamGia(request.getSoThanhVienThamGia());
        daoTrang.setThoiGianBatDau(request.getThoiGianBatDau());
        daoTrang.setNguoiTruTri(request.getNguoiTruTri());
        daoTrang.setDonDangKys(null);
        daoTrang.setPhatTuDaoTrangs(null);
        daoTrangRepo.save(daoTrang);
        daoTrang.setDonDangKys(themListDonDangKy(daoTrang.getId(), request.getDonDangKys()));
        daoTrang.setPhatTuDaoTrangs(themListPhatTuDaoTrang(daoTrang.getId(), request.getPhatTuDaoTrangs()));
        return _responseObject.responseSuccess("Them dao trang thanh cong", _converter.entityToDTO(daoTrang));
    }

    @Override
    public DaoTrang suaDaoTrang(DaoTrang daoTrangSua) {
        Optional<DaoTrang> daoTrangOptional = daoTrangRepo.findById(daoTrangSua.getId());
        if (daoTrangOptional.isEmpty()) {
            return null;
        }
        DaoTrang daoTrang = daoTrangOptional.get();
        daoTrang.setDaKetThuc(true);
        daoTrang.setNoiDung(daoTrangSua.getNoiDung());
        daoTrang.setNoiToChuc(daoTrangSua.getNoiToChuc());
        daoTrang.setSoThanhVienThamGia(daoTrangSua.getSoThanhVienThamGia());
        daoTrang.setNguoiTruTri(daoTrangSua.getNguoiTruTri());
        daoTrang.setDonDangKys(daoTrangSua.getDonDangKys());
        daoTrang.setPhatTuDaoTrangs(daoTrangSua.getPhatTuDaoTrangs());
        daoTrangRepo.save(daoTrang);
        return daoTrang;
    }
}
