package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.repository.DonDangKyRepo;
import SpringBoot.Test.repository.TrangThaiDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TrangThaiDonConverter {
    @Autowired
    private TrangThaiDonRepo _trangThaiDonRepo;
    @Autowired
    private DonDangKyRepo _donDangKyRepo;
    private final DonDangKyConverter _converter;
    public TrangThaiDonConverter(DonDangKyConverter converter) {
        _converter = converter;
    }

    public TrangThaiDonDTO entityToDTO(TrangThaiDon trangThaiDon) {
        TrangThaiDonDTO trangThaiDonDTO = new TrangThaiDonDTO();
        trangThaiDonDTO.setTenTrangThai(trangThaiDon.getTenTrangThai());
        trangThaiDonDTO.setDonDangKys(_donDangKyRepo.findAll().stream().filter(x -> x.getTrangThaiDon().getId() == trangThaiDon.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return trangThaiDonDTO;
    }
}
