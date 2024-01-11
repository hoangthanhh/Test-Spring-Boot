package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.RefreshToken;
import SpringBoot.Test.payload.DataResponse.PhatTuDTO;
import SpringBoot.Test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class PhatTuConverter {
    @Autowired
    private QuyenHanRepo _quyenHanRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;
    private final RefreshTokenConverter _converter;
    private final XacNhanEmailConverter _xacNhanConverter;
    public PhatTuConverter(RefreshTokenConverter converter, XacNhanEmailConverter xacNhanConverter) {
        _converter = converter;
        _xacNhanConverter = xacNhanConverter;
    }
    @Autowired
    private ChuaRepo _chuaRepo;
    @Autowired
    private RefreshTokenRepo _refreshRepo;
    public PhatTuDTO entityToDTO(PhatTu phatTu) {
        PhatTuDTO phatTuDTO = new PhatTuDTO();
        phatTuDTO.setId(phatTu.getId());
        phatTuDTO.setEmail(phatTu.getEmail());
        phatTuDTO.setAnhChup(phatTu.getAnhChup());
        phatTuDTO.setMatKhau(phatTu.getMatKhau());
        phatTuDTO.setGioiTinh(phatTu.getGioiTinh());
        phatTuDTO.setNgaySinh(phatTu.getNgaysinh());
        phatTuDTO.setDaHoanTuc(phatTu.getDaHoanTuc());
        phatTuDTO.setNgayCapNhat(phatTu.getNgayCapNhat());
        phatTuDTO.setNgayHoanTuc(phatTu.getNgayHoanTuc());
        phatTuDTO.setPhapDanh(phatTu.getPhapDanh());
        phatTuDTO.setSoDienThoai(phatTu.getSoDienThoai());
        phatTuDTO.setTenTaiKhoan(phatTu.getTenTaiKhoan());
        phatTuDTO.setTenChua(_chuaRepo.findById(phatTu.getChua().getId()).get().getTenChua());
        phatTuDTO.setTenQuyenHan(_quyenHanRepo.findById(phatTu.getQuyenHan().getId()).get().getTenQuyenHan());
        phatTuDTO.setRefreshTokens(_refreshRepo.findAll().stream().filter(x->x.getPhatTu().getId() == phatTu.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return phatTuDTO;
    }
}
