package SpringBoot.Test.service;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.repository.BinhLuanBaiVietRepo;
import SpringBoot.Test.repository.NguoiDungThichBinhLuanBaiVietRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungThichBinhLuanBaiVietService implements INguoiDungThichBinhLuan {
    @Autowired
    private NguoiDungThichBinhLuanBaiVietRepo nguoiDungThichBinhLuanBaiVietRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private BinhLuanBaiVietRepo binhLuanBaiVietRepo;
    @Override
    public String themListNguoiDungThichBL(List<NguoiDungThichBinhLuanBaiViet> list, int phatTuId, int binhLuanBaiVietId) {
        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuId);
        Optional<BinhLuanBaiViet> binhLuanBaiVietOptional = binhLuanBaiVietRepo.findById(binhLuanBaiVietId);
        if (phatTuOptional.isEmpty()) {
            return null;
        }
        if (binhLuanBaiVietOptional.isEmpty()) {
            return null;
        }
        PhatTu phatTu = phatTuOptional.get();
        BinhLuanBaiViet binhLuanBaiViet = binhLuanBaiVietOptional.get();
       for (NguoiDungThichBinhLuanBaiViet nguoiDung: list) {
           if (phatTuId == phatTu.getId() && binhLuanBaiVietId == binhLuanBaiViet.getId()) {
               phatTuRepo.save(phatTu);
               binhLuanBaiVietRepo.save(binhLuanBaiViet);
               nguoiDung.setPhatTu(phatTu);
               nguoiDung.setBinhLuanBaiViet(binhLuanBaiViet);
               nguoiDungThichBinhLuanBaiVietRepo.save(nguoiDung);
           }
           else {
               return "Danh sach nay ko thoa man";
           }
       }
       return "Them thanh cong";
    }

    @Override
    public NguoiDungThichBinhLuanBaiViet suaNguoiDungThichBLBaiViet(NguoiDungThichBinhLuanBaiViet nguoiDungSua) {
        Optional<NguoiDungThichBinhLuanBaiViet> nguoiDungThichBinhLuanBaiVietOptional = nguoiDungThichBinhLuanBaiVietRepo.findById(nguoiDungSua.getId());
        if (nguoiDungThichBinhLuanBaiVietOptional.isEmpty()) {
            return null;
        }
        NguoiDungThichBinhLuanBaiViet nguoiDungThichBinhLuanBaiViet = nguoiDungThichBinhLuanBaiVietOptional.get();
        nguoiDungThichBinhLuanBaiViet.setThoiGianLike(nguoiDungSua.getThoiGianLike());
        nguoiDungThichBinhLuanBaiViet.setPhatTu(nguoiDungSua.getPhatTu());
        nguoiDungThichBinhLuanBaiViet.setBinhLuanBaiViet(nguoiDungSua.getBinhLuanBaiViet());
        nguoiDungThichBinhLuanBaiVietRepo.save(nguoiDungThichBinhLuanBaiViet);
        return nguoiDungThichBinhLuanBaiViet;
    }

    @Override
    public NguoiDungThichBinhLuanBaiViet xoaNguoiDungThichBLBaiViet(int nguoiDungThichBLBaiVietId) {
        Optional<NguoiDungThichBinhLuanBaiViet> nguoiDungOp = nguoiDungThichBinhLuanBaiVietRepo.findById(nguoiDungThichBLBaiVietId);
        if (nguoiDungOp.isEmpty()) {
            return null;
        }
        NguoiDungThichBinhLuanBaiViet nguoiDung = nguoiDungOp.get();
        BinhLuanBaiViet binhLuanBaiViet = nguoiDung.getBinhLuanBaiViet();
        if (binhLuanBaiViet != null) {
            binhLuanBaiViet.setSoLuotThich(binhLuanBaiViet.getSoLuotThich() - 1);
            binhLuanBaiVietRepo.save(binhLuanBaiViet);
        }
        nguoiDungThichBinhLuanBaiVietRepo.delete(nguoiDung);
        nguoiDung.setDaXoa(true);
//        nguoiDungThichBinhLuanBaiVietRepo.save(nguoiDung);
        return nguoiDung;
    }

    @Override
    public List<NguoiDungThichBinhLuanBaiViet> getAllNguoiDungThichBinhLuanBaiViet() {
        return nguoiDungThichBinhLuanBaiVietRepo.findAll();
    }
}
