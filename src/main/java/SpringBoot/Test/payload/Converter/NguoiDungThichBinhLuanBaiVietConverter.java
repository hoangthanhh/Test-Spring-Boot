package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import SpringBoot.Test.payload.DataResponse.NguoiDungThichBinhLuanBaiVietDTO;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NguoiDungThichBinhLuanBaiVietConverter {
    @Autowired
    private PhatTuRepo _phatTuRepo;
    public NguoiDungThichBinhLuanBaiVietDTO entityToDTO(NguoiDungThichBinhLuanBaiViet nguoiDungThichBinhLuanBaiViet) {
        NguoiDungThichBinhLuanBaiVietDTO nguoi =new NguoiDungThichBinhLuanBaiVietDTO();
        nguoi.setThoiGianLike(nguoiDungThichBinhLuanBaiViet.getThoiGianLike());
        nguoi.setDaXoa(nguoiDungThichBinhLuanBaiViet.getDaXoa());
        nguoi.setTenTaiKhoan(_phatTuRepo.findById(nguoiDungThichBinhLuanBaiViet.getPhatTu().getId()).get().getTenTaiKhoan());
        nguoi.setNguoiDungThichBinhLuanBaiVietId(nguoiDungThichBinhLuanBaiViet.getId());
        return nguoi;
    }
}
