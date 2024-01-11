package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.payload.DataResponse.BinhLuanBaiVietDTO;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.LoaiBaiVietRepo;
import SpringBoot.Test.repository.NguoiDungThichBinhLuanBaiVietRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BinhLuanBaiVietConverter {
    @Autowired
    private NguoiDungThichBinhLuanBaiVietRepo _nguoiDungThichBinhLuanBaiVietRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;
    @Autowired
    private BaiVietRepo _baiVietRepo;
    @Autowired
    private LoaiBaiVietRepo _loaiBaiVietRepo;

    private final NguoiDungThichBinhLuanBaiVietConverter _converter;
    public BinhLuanBaiVietConverter(NguoiDungThichBinhLuanBaiVietConverter converter) {
        _converter = converter;
    }

    public BinhLuanBaiVietDTO entityToDTO(BinhLuanBaiViet binhLuanBaiViet) {
        BinhLuanBaiVietDTO binhLuanBaiVietDTO = new BinhLuanBaiVietDTO();
        binhLuanBaiVietDTO.setBinhLuan(binhLuanBaiViet.getBinhLuan());
        binhLuanBaiVietDTO.setSoLuotThich(binhLuanBaiViet.getSoLuotThich());
        binhLuanBaiVietDTO.setThoiGianTao(binhLuanBaiViet.getThoiGianTao());
        binhLuanBaiVietDTO.setTenTaiKhoan(_phatTuRepo.findById(binhLuanBaiViet.getPhatTu().getId()).get().getTenTaiKhoan());
        binhLuanBaiVietDTO.setLoaiBaiVietId(_loaiBaiVietRepo.findById(binhLuanBaiViet.getBaiViet().getId()).get().getId());
        binhLuanBaiVietDTO.setNguoiDungThichBinhLuanBaiViets(_nguoiDungThichBinhLuanBaiVietRepo.findAll().stream().filter(x->x.getBinhLuanBaiViet().getId() == binhLuanBaiViet.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        binhLuanBaiVietDTO.setBinhLuanBaiVietId(binhLuanBaiViet.getId());
        return binhLuanBaiVietDTO;
    }
}
