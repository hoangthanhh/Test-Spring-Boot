package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.payload.DataResponse.DonDangKyDTO;
import SpringBoot.Test.repository.DaoTrangRepo;
import SpringBoot.Test.repository.PhatTuDaoTrangRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.TrangThaiDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DonDangKyConverter {
    @Autowired
    private DaoTrangRepo _daoTrangRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;
    @Autowired
    private TrangThaiDonRepo _trangThaiDonRepo;
    public DonDangKyDTO entityToDTO(DonDangKy donDangKy){
        DonDangKyDTO don = new DonDangKyDTO();
        don.setNgayGuiDon(donDangKy.getNgayGuiDon());
        don.setNgayXuLy(donDangKy.getNgayXuLy());
        don.setNguoiXuLy(donDangKy.getNguoiXuLy());
        don.setDaKetThuc(_daoTrangRepo.findById(donDangKy.getDaoTrang().getId()).get().getDaKetThuc());
        don.setTenTrangThai(_trangThaiDonRepo.findById(donDangKy.getTrangThaiDon().getId()).get().getTenTrangThai());
        don.setTenTaiKhoan(_phatTuRepo.findById(donDangKy.getPhatTu().getId()).get().getTenTaiKhoan());
        don.setId(donDangKy.getId());
        return don;
    }
}
