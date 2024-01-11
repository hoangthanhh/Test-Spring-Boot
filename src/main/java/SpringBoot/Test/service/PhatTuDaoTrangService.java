package SpringBoot.Test.service;

import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import SpringBoot.Test.repository.DaoTrangRepo;
import SpringBoot.Test.repository.PhatTuDaoTrangRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhatTuDaoTrangService implements IPhatTuDaoTrang {
    @Autowired
    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private DaoTrangRepo daoTrangRepo;

    @Override
    public String themListPhatTuDaoTrang(List<PhatTuDaoTrang> list, int phatTuId, int daoTrangId) {
        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuId);
        Optional<DaoTrang> daoTrangOptional = daoTrangRepo.findById(daoTrangId);
        if (phatTuOptional.isEmpty()) {
            return "Khong tim thay phat tu voi Id: " + phatTuId;
        }
        if (daoTrangOptional.isEmpty()) {
            return "Khong tim thay dao trang voi Id: " + daoTrangId;
        }
        PhatTu phatTu = phatTuOptional.get();
        DaoTrang daoTrang = daoTrangOptional.get();
        for (PhatTuDaoTrang phatTuDaoTrang: list) {
            if (phatTuId == phatTu.getId() && daoTrangId == daoTrang.getId()) {
                phatTuRepo.save(phatTu);
                daoTrangRepo.save(daoTrang);
                phatTuDaoTrang.setPhatTu(phatTu);
                phatTuDaoTrang.setDaoTrang(daoTrang);
                phatTuDaoTrangRepo.save(phatTuDaoTrang);
            }
            else {
                return "Danh sach nay khong thoa man";
            }
        }
        return "Them thanh cong";
    }

    @Override
    public PhatTuDaoTrang suaPhatTuDaoTrang(PhatTuDaoTrang phatTuDaoTrangSua) {
        Optional<PhatTuDaoTrang> phatTuDaoTrangOptional = phatTuDaoTrangRepo.findById(phatTuDaoTrangSua.getId());
        if (phatTuDaoTrangOptional.isEmpty()) {
            return null;
        }
        PhatTuDaoTrang phatTuDaoTrang = phatTuDaoTrangOptional.get();
        phatTuDaoTrang.setDaThamGia(true);
        phatTuDaoTrang.setLyDoKhongThamGia(phatTuDaoTrangSua.getLyDoKhongThamGia());
        phatTuDaoTrang.setDaoTrang(phatTuDaoTrangSua.getDaoTrang());
        phatTuDaoTrang.setPhatTu(phatTuDaoTrangSua.getPhatTu());
        phatTuDaoTrangRepo.save(phatTuDaoTrang);
        return phatTuDaoTrang;
    }
}
