package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.LoaiBaiViet;
import SpringBoot.Test.payload.DataResponse.LoaiBaiVietDTO;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.LoaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class LoaiBaiVietConverter {
    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    private BaiVietRepo _baiVietRepo;
    private final BaiVietConverter _converter;
    public LoaiBaiVietConverter(BaiVietConverter converter) {
        _converter = converter;
    }
    public LoaiBaiVietDTO entityToDTO(LoaiBaiViet loaiBaiViet) {
        LoaiBaiVietDTO loaiBaiVietDTO = new LoaiBaiVietDTO();
        loaiBaiVietDTO.setTenLoai(loaiBaiViet.getTenLoai());
        loaiBaiVietDTO.setBaiViets(_baiVietRepo.findAll().stream().filter(x -> x.getLoaiBaiViet().getId() == loaiBaiViet.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return loaiBaiVietDTO;
    }
}
