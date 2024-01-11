package SpringBoot.Test.service;

import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.QuyenHan;
import SpringBoot.Test.payload.Converter.QuyenHanConverter;
import SpringBoot.Test.payload.DataRequest.ThemPhatTuRequest;
import SpringBoot.Test.payload.DataRequest.ThemQuyenHanRequest;
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
public class QuyenHanService implements IQuyenHan{
    @Autowired
    private QuyenHanRepo quyenHanRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private ChuaRepo chuaRepo;
    private final QuyenHanConverter _converter;
    private final ResponseObject<QuyenHanDTO> _responseObject;
    public QuyenHanService(QuyenHanConverter converter, ResponseObject<QuyenHanDTO> responseObject){
        _converter = converter;
        _responseObject = responseObject;
    }
    private Set<PhatTu> themListPhatTu(int quyenHanId, List<ThemPhatTuRequest> requests) {
        var quyenHan = quyenHanRepo.findById(quyenHanId);
        if (quyenHan.isEmpty()) {
            return null;
        }
        Set<PhatTu> set = new HashSet<>();
        for (var request: requests) {
            PhatTu phatTu = new PhatTu();
            phatTu.setQuyenHan(quyenHan.get());
            phatTu.setTenTaiKhoan(request.getTenTaiKhoan());
            phatTu.setAnhChup(request.getAnhChup());
            phatTu.setDaHoanTuc(request.getDaHoanTuc());
            phatTu.setEmail(request.getEmail());
            phatTu.setGioiTinh(request.getGioiTinh());
            phatTu.setNgayCapNhat(request.getNgayCapNhat());
            phatTu.setNgayHoanTuc(request.getNgayHoanTuc());
            phatTu.setNgaysinh(request.getNgaySinh());
            phatTu.setMatKhau(request.getMatKhau());
            phatTu.setPhapDanh(request.getPhapDanh());
            phatTu.setSoDienThoai(request.getSoDienThoai());
            var chua = chuaRepo.findById(request.getChuaId()).get();
            phatTu.setChua(chua);
            set.add(phatTu);
        }
        phatTuRepo.saveAll(set);
        quyenHan.get().setPhatTus(set);
        quyenHanRepo.save(quyenHan.get());
        return set;
    }

    @Override
    public ResponseObject<QuyenHanDTO> themQuyenHanRequest(ThemQuyenHanRequest request) {
        QuyenHan quyenHan = new QuyenHan();
        quyenHan.setTenQuyenHan(request.getTenQuyenHan());
        quyenHan.setPhatTus(null);
        quyenHanRepo.save(quyenHan);
        quyenHan.setPhatTus(themListPhatTu(quyenHan.getId(), request.getPhatTus()));
        return _responseObject.responseSuccess("Them quyen han thanh cong", _converter.entityToDTO(quyenHan));
    }

    @Override
    public QuyenHan suaQuyenHan(QuyenHan quyenHanSua) {
        Optional<QuyenHan> quyenHanOptional = quyenHanRepo.findById(quyenHanSua.getId());
        if (quyenHanOptional.isEmpty()) {
            return null;
        }
        QuyenHan quyenHan = quyenHanOptional.get();
        quyenHan.setTenQuyenHan(quyenHanSua.getTenQuyenHan());
        quyenHan.setPhatTus(quyenHanSua.getPhatTus());
        quyenHanRepo.save(quyenHan);
        return quyenHan;
    }
}
