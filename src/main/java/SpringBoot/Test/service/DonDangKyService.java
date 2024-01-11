package SpringBoot.Test.service;

import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataResponse.DonDangKyDTO;
import SpringBoot.Test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DonDangKyService implements IDonDangKy{
    @Autowired
    private DonDangKyRepo donDangKyRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private TrangThaiDonRepo trangThaiDonRepo;
    @Autowired
    private DaoTrangRepo daoTrangRepo;

    @Override
    public String themListDonDangKy(List<DonDangKy> list, int trangThaiDonId) {
        Optional<TrangThaiDon> trangThaiDonOptional = trangThaiDonRepo.findById(trangThaiDonId);
//        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuId)
//        DaoTrang daoTrang = daoTrangRepo.findById(daoTrangId).orElseThrow(()->new RuntimeException("Khong tim thay dao trang voi Id:" + daoTrangId));
        if (trangThaiDonOptional.isEmpty()) {
            return "Khong tim thay trang thai don voi Id:" + trangThaiDonId;
        }
        TrangThaiDon trangThaiDon = trangThaiDonOptional.get();
//        PhatTu phatTu = phatTuOptional.get();
        for (DonDangKy don : list) {
            if (trangThaiDonId == trangThaiDon.getId()) {
                trangThaiDonRepo.save(trangThaiDon);
                don.setTrangThaiDon(trangThaiDon);
//                daoTrangRepo.save(daoTrang);
//                phatTuRepo.save(phatTu);
                donDangKyRepo.save(don);
            }
            else {
                return "Chi tiet nay khong thoa man";
            }
        }
        return "Them thanh cong";
    }

    @Override
    public DonDangKy suaDonDangKy(DonDangKy donDangKySua) {
        Optional<DonDangKy> donDangKyOptional = donDangKyRepo.findById(donDangKySua.getId());
        if (donDangKyOptional.isEmpty()) {
            return null;
        }
        DonDangKy donDangKy = donDangKyOptional.get();
        donDangKy.setNgayXuLy(LocalDate.now());
        donDangKy.setNguoiXuLy(donDangKySua.getNguoiXuLy());
        donDangKy.setTrangThaiDon(donDangKySua.getTrangThaiDon());
        donDangKyRepo.save(donDangKy);
        return donDangKy;
    }
}
