package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.TrangThaiBaiViet;
import SpringBoot.Test.payload.DataResponse.TrangThaiBaiVietDTO;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.TrangThaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TrangThaiBaiVietConverter {
    @Autowired
    private TrangThaiBaiVietRepo _trangThaiBaiVietRepo;
    @Autowired
    private BaiVietRepo _baiVietRepo;
    private final BaiVietConverter _converter;
    public TrangThaiBaiVietConverter(BaiVietConverter converter) {
        _converter = converter;
    }
    public TrangThaiBaiVietDTO entityToDTO(TrangThaiBaiViet trangThaiBaiViet) {
        TrangThaiBaiVietDTO trangThaiBaiVietDTO = new TrangThaiBaiVietDTO();
        trangThaiBaiVietDTO.setTenTrangThai(trangThaiBaiViet.getTenTrangThai());
        trangThaiBaiVietDTO.setBaiViets(_baiVietRepo.findAll().stream().filter(x -> x.getTrangThaiBaiViet().getId() == trangThaiBaiViet.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return trangThaiBaiVietDTO;
    }
}
