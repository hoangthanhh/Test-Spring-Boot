package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataResponse.ChuaDTO;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.repository.ChuaRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChuaConverter {
    @Autowired
    private ChuaRepo _chuaRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;
    private final PhatTuConverter _converter;
    public ChuaConverter(PhatTuConverter converter) {
        _converter = converter;
    }
    public ChuaDTO entityToDTO(Chua chua) {
        ChuaDTO chuaDTO = new ChuaDTO();
        chuaDTO.setTenChua(chua.getTenChua());
        chuaDTO.setDiaChi(chua.getDiaChi());
        chuaDTO.setNgayThanhLap(chua.getNgayThanhLap());
        chuaDTO.setNguoiTruTri(chua.getNguoiTruTri());
        chuaDTO.setPhatTus(_phatTuRepo.findAll().stream().filter(x -> x.getChua().getId() == chua.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return chuaDTO;
    }
}
