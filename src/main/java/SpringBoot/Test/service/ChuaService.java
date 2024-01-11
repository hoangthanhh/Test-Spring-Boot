package SpringBoot.Test.service;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.payload.Converter.ChuaConverter;
import SpringBoot.Test.payload.Converter.DaoTrangConverter;
import SpringBoot.Test.payload.DataRequest.ThemChuaRequest;
import SpringBoot.Test.payload.DataRequest.ThemDonDangKyRequest;
import SpringBoot.Test.payload.DataRequest.ThemPhatTuRequest;
import SpringBoot.Test.payload.DataResponse.ChuaDTO;
import SpringBoot.Test.payload.DataResponse.DaoTrangDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.ChuaRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.QuyenHanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ChuaService implements IChua {
    @Autowired
    private ChuaRepo chuaRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private QuyenHanRepo quyenHanRepo;

    private final ChuaConverter _converter;
    private final ResponseObject<ChuaDTO> _responseObject;
    public ChuaService(ChuaConverter converter, ResponseObject<ChuaDTO> responseObject){
        _converter = converter;
        _responseObject = responseObject;
    }
    private Set<PhatTu> themListPhatTu(int chuaId, List<ThemPhatTuRequest> requests) {
        var chua = chuaRepo.findById(chuaId);
        if (chua.isEmpty()) {
            return null;
        }
        Set<PhatTu> set = new HashSet<>();
        for (var request : requests) {
            PhatTu phatTu = new PhatTu();
            phatTu.setChua(chua.get());
            phatTu.setSoDienThoai(request.getSoDienThoai());
            phatTu.setNgaysinh(request.getNgaySinh());
            phatTu.setMatKhau(request.getMatKhau());
            phatTu.setEmail(request.getEmail());
            phatTu.setPhapDanh(request.getPhapDanh());
            phatTu.setNgayHoanTuc(request.getNgayHoanTuc());
            phatTu.setNgayCapNhat(request.getNgayCapNhat());
            phatTu.setAnhChup(request.getAnhChup());
            phatTu.setGioiTinh(request.getGioiTinh());
            phatTu.setTenTaiKhoan(request.getTenTaiKhoan());
            phatTu.setDaHoanTuc(request.getDaHoanTuc());
            var quyenHan = quyenHanRepo.findById(request.getQuyenHanId()).get();
            phatTu.setQuyenHan(quyenHan);
            set.add(phatTu);
        }
        phatTuRepo.saveAll(set);
        chua.get().setPhatTus(set);
        chuaRepo.save(chua.get());
        return set;
    }

    @Override
    public ResponseObject<ChuaDTO> themChuaRequest(ThemChuaRequest request) {
        Chua chua= new Chua();
        chua.setTenChua(request.getTenChua());
        chua.setDiaChi(request.getDiaChi());
        chua.setNgayThanhLap(request.getNgayThanhLap());
        chua.setNguoiTruTri(request.getNguoiTruTri());
        chua.setPhatTus(null);
        chuaRepo.save(chua);
        chua.setPhatTus(themListPhatTu(chua.getId(), request.getPhatTus()));
        return _responseObject.responseSuccess("Them chua thanh cong", _converter.entityToDTO(chua));
    }

    @Override
    public Chua suaChua(Chua chuaSua) {
        Optional<Chua> chuaOptional = chuaRepo.findById(chuaSua.getId());
        if (chuaOptional.isEmpty()) {
            return null;
        }
        Chua chua = chuaOptional.get();
        chua.setTenChua(chuaSua.getTenChua());
        chua.setDiaChi(chuaSua.getDiaChi());
        chua.setNgayThanhLap(chuaSua.getNgayThanhLap());
        chua.setNguoiTruTri(chuaSua.getNguoiTruTri());
        chua.setPhatTus(chuaSua.getPhatTus());
        chuaRepo.save(chua);
        return chua;
    }
}
