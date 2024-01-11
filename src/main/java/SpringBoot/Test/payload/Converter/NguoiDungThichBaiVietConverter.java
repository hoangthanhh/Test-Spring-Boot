package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.NguoiDungThichBaiViet;
import SpringBoot.Test.payload.DataResponse.NguoiDungThichBaiVietDTO;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NguoiDungThichBaiVietConverter {
    @Autowired
    private BaiVietRepo _baiVietRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;

    public NguoiDungThichBaiVietDTO entityToDTO(NguoiDungThichBaiViet nguoiDungThichBaiViet) {
        NguoiDungThichBaiVietDTO nguoi = new NguoiDungThichBaiVietDTO();
        nguoi.setThoiGianThich(nguoiDungThichBaiViet.getThoiGianThich());
        nguoi.setDaXoa(nguoiDungThichBaiViet.getDaXoa());
        nguoi.setTenTaiKhoan(_phatTuRepo.findById(nguoiDungThichBaiViet.getPhatTu().getId()).get().getTenTaiKhoan());
        nguoi.setLoaiBaiVietId(_baiVietRepo.findById(nguoiDungThichBaiViet.getBaiViet().getId()).get().getLoaiBaiViet().getId());
        nguoi.setNguoiDungThichBaiVietId(nguoiDungThichBaiViet.getId());
        return nguoi;
    }
}
