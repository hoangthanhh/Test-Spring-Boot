package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.payload.DataResponse.BaiVietDTO;
import SpringBoot.Test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BaiVietConverter {
    @Autowired
    private LoaiBaiVietRepo _loaiBaiVietRepo;
    @Autowired
    private TrangThaiBaiVietRepo _trangThaiBaiVietRepo;
    @Autowired
    private BinhLuanBaiVietRepo _binhLuanBaiVietRepo;
    @Autowired
    private NguoiDungThichBaiVietRepo _nguoiDungThichBaiVietRepo;
    private final BinhLuanBaiVietConverter _converter;
    private final NguoiDungThichBaiVietConverter _ngConverter;
    public BaiVietConverter(BinhLuanBaiVietConverter converter, NguoiDungThichBaiVietConverter ngConverter) {
        _converter = converter;
        _ngConverter = ngConverter;
    }
    @Autowired
    private PhatTuRepo _phatTuRepo;
    public BaiVietDTO entityToDTO(BaiViet baiViet) {
        BaiVietDTO baiVietDTO = new BaiVietDTO();
        baiVietDTO.setTieuDe(baiViet.getTieuDe());
        baiVietDTO.setMoTa(baiViet.getMoTa());
        baiVietDTO.setNoiDung(baiViet.getNoiDung());
        baiVietDTO.setNguoiDuyetBaiVietId(baiViet.getNguoiDuyetBaiVietId());
        baiVietDTO.setSoLuotThich(baiViet.getSoLuotThich());
        baiVietDTO.setSoLuotBinhLuan(baiViet.getSoLuotBinhLuan());
        baiVietDTO.setThoiGianDang(baiViet.getThoiGianDang());
        baiVietDTO.setThoiGianCapNhat(baiViet.getThoiGianCapNhat());
        baiVietDTO.setThoiGianXoa(baiViet.getThoiGianXoa());
        baiVietDTO.setDaXoa(baiViet.getDaXoa());
        baiVietDTO.setTenTaiKhoan(_phatTuRepo.findById(baiViet.getPhatTu().getId()).get().getTenTaiKhoan());
        baiVietDTO.setTenTrangThai(_trangThaiBaiVietRepo.findById(baiViet.getTrangThaiBaiViet().getId()).get().getTenTrangThai());
        baiVietDTO.setTenLoai(_loaiBaiVietRepo.findById(baiViet.getLoaiBaiViet().getId()).get().getTenLoai());
        baiVietDTO.setBinhLuanBaiViets(_binhLuanBaiVietRepo.findAll().stream().filter(x->x.getBaiViet().getId() == baiViet.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        baiVietDTO.setNguoiDungThichBaiViets(_nguoiDungThichBaiVietRepo.findAll().stream().filter(x->x.getBaiViet().getId() == baiViet.getId()).map(_ngConverter::entityToDTO).collect(Collectors.toSet()));
        baiVietDTO.setId(baiViet.getId());
        return baiVietDTO;
    }
}
