package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import SpringBoot.Test.payload.DataResponse.DonDangKyDTO;
import SpringBoot.Test.payload.DataResponse.PhatTuDaoTrangDTO;
import SpringBoot.Test.repository.DaoTrangRepo;
import SpringBoot.Test.repository.DonDangKyRepo;
import SpringBoot.Test.repository.PhatTuDaoTrangRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhatTuDaoTrangConverter {
    @Autowired
    private DaoTrangRepo _daoTrangRepo;
    @Autowired
    private DonDangKyRepo _donDangKyRepo;
    @Autowired
    private PhatTuDaoTrangRepo _phatTuDaoTrangRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;

    public PhatTuDaoTrangDTO entityToDTO(PhatTuDaoTrang phatTuDaoTrang){
        PhatTuDaoTrangDTO phatTuDaoTrangDTO = new PhatTuDaoTrangDTO();
        phatTuDaoTrangDTO.setId(phatTuDaoTrang.getId());
        phatTuDaoTrangDTO.setDaThamGia(phatTuDaoTrang.getDaThamGia());
        phatTuDaoTrangDTO.setLyDoKhongThamGia(phatTuDaoTrang.getLyDoKhongThamGia());
        phatTuDaoTrangDTO.setTenTaiKhoan(_phatTuRepo.findById(phatTuDaoTrang.getPhatTu().getId()).get().getTenTaiKhoan());
        return phatTuDaoTrangDTO;
    }
}
