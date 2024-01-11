package SpringBoot.Test.service;

import SpringBoot.Test.entity.*;
import SpringBoot.Test.payload.Converter.PhatTuConverter;
import SpringBoot.Test.payload.DataRequest.*;
import SpringBoot.Test.payload.DataResponse.PhatTuDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhatTuService implements IPhatTu{
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Autowired
    private ChuaRepo chuaRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private QuyenHanRepo quyenHanRepo;
    @Autowired
    private XacNhanEmailRepo xacNhanEmailRepo;
    private final PhatTuConverter _converter;
    private final ResponseObject<PhatTuDTO> _responseObject;
    public PhatTuService(PhatTuConverter converter, ResponseObject<PhatTuDTO> responseObject) {
        _converter = converter;
        _responseObject = responseObject;
    }

    private Set<RefreshToken> themListRefreshToken(int phatTuId, List<ThemRefreshTokenRequest> requests) {
        var phatTu = phatTuRepo.findById(phatTuId);
        if (phatTu.isEmpty()) {
            return null;
        }
        Set<RefreshToken> set = new HashSet<>();
        for (var request: requests) {
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setPhatTu(phatTu.get());
            refreshToken.setToken(request.getToken());
            refreshToken.setThoiGianHetHan(request.getThoiGianHetHan());
            set.add(refreshToken);
        }
        refreshTokenRepo.saveAll(set);
        phatTu.get().setRefreshTokens(set);
        phatTuRepo.save(phatTu.get());
        return set;
    }
    private Set<XacNhanEmail> themListXacNhanEmail(int phatTuId, List<ThemXacNhanEmailRequest> requests) {
        var phatTu = phatTuRepo.findById(phatTuId);
        if (phatTu.isEmpty()) {
            return null;
        }
        Set<XacNhanEmail> set = new HashSet<>();
        for (var request: requests) {
            XacNhanEmail xacNhanEmail = new XacNhanEmail();
            xacNhanEmail.setPhatTu(phatTu.get());
            xacNhanEmail.setMaXacNhan(request.getMaXacNhan());
            xacNhanEmail.setThoiGianHetHan(request.getThoiGianHetHan());
            xacNhanEmail.setDaXacNhan(request.getDaXacNhan());
            set.add(xacNhanEmail);
        }
        xacNhanEmailRepo.saveAll(set);
        phatTu.get().setXacNhanEmails(set);
        phatTuRepo.save(phatTu.get());
        return set;
    }
    private Set<BaiViet> themListBaiViet(int phatTuId, List<ThemBaiVietRequest> requests) {
        var phatTu = phatTuRepo.findById(phatTuId);
        if (phatTu.isEmpty()) {
            return null;
        }
        Set<BaiViet> set = new HashSet<>();
        for (var request: requests) {
            BaiViet baiViet = new BaiViet();
            baiViet.setPhatTu(phatTu.get());
            baiViet.setTieuDe(request.getTieuDe());
            baiViet.setMoTa(request.getMoTa());
            baiViet.setNoiDung(request.getNoiDung());
            set.add(baiViet);
        }
        baiVietRepo.saveAll(set);
        phatTu.get().setBaiViets(set);
        phatTuRepo.save(phatTu.get());
        return set;
    }

    @Override
    public ResponseObject<PhatTuDTO> themPhatTuRequest(ThemPhatTuRequest request) {
        var chua = chuaRepo.findById(request.getChuaId());
        if (chua.isEmpty()) {
            return _responseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Chua khong ton tai", null);
        }
        var quyenHan = quyenHanRepo.findById(request.getQuyenHanId());
        if (quyenHan.isEmpty()) {
            return _responseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Quyen han khong ton tai", null);
        }
        PhatTu phatTu = new PhatTu();
        phatTu.setTenTaiKhoan(request.getTenTaiKhoan());
        phatTu.setAnhChup(request.getAnhChup());
        phatTu.setDaHoanTuc(request.getDaHoanTuc());
        phatTu.setEmail(request.getEmail());
        phatTu.setGioiTinh(request.getGioiTinh());
        phatTu.setNgayHoanTuc(request.getNgayHoanTuc());
        phatTu.setNgaysinh(request.getNgaySinh());
        phatTu.setMatKhau(request.getMatKhau());
        phatTu.setPhapDanh(request.getPhapDanh());
        phatTu.setSoDienThoai(request.getSoDienThoai());
        phatTu.setChua(chua.get());
        phatTu.setQuyenHan(quyenHan.get());
        phatTu.setRefreshTokens(null);
        phatTu.setXacNhanEmails(null);
        phatTuRepo.save(phatTu);
        phatTu.setRefreshTokens(themListRefreshToken(phatTu.getId(), request.getRefreshTokens()));
        phatTu.setXacNhanEmails(themListXacNhanEmail(phatTu.getId(), request.getXacNhanEmails()));
        phatTu.setBaiViets(themListBaiViet(phatTu.getId(), request.getBaiViets()));
        return _responseObject.responseSuccess("Them phat tu thanh cong", _converter.entityToDTO(phatTu));
    }

    @Override
    public PhatTu suaPhatTu(PhatTuDTO phatTuSua) {
        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuSua.getId());
        if (phatTuOptional.isEmpty()) {
            return null;
        }
        PhatTu phatTu = phatTuOptional.get();
        phatTu.setTenTaiKhoan(phatTuSua.getTenTaiKhoan());
        phatTu.setAnhChup(phatTuSua.getAnhChup());
        phatTu.setDaHoanTuc(true);
        phatTu.setEmail(phatTuSua.getEmail());
        phatTu.setGioiTinh(phatTuSua.getGioiTinh());
        phatTu.setNgayHoanTuc(LocalDate.now());
        phatTu.setNgaysinh(phatTuSua.getNgaySinh());
        phatTu.setMatKhau(phatTuSua.getMatKhau());
        phatTu.setPhapDanh(phatTuSua.getPhapDanh());
        phatTu.setSoDienThoai(phatTuSua.getSoDienThoai());
        phatTuRepo.save(phatTu);
        return phatTu;
    }
}
