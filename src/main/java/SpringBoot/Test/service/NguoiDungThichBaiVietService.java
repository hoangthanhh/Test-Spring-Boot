package SpringBoot.Test.service;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.NguoiDungThichBaiViet;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.NguoiDungThichBaiVietRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungThichBaiVietService implements INguoiDungThichBaiViet{
    @Autowired
    private NguoiDungThichBaiVietRepo nguoiDungThichBaiVietRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Override
    public String themListNguoiDungThichBaiViet(List<NguoiDungThichBaiViet> list, int phatTuId, int baiVietId) {
        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuId);
        Optional<BaiViet> baiVietOptional = baiVietRepo.findById(baiVietId);
        if (phatTuOptional.isEmpty()) {
            return "Khong tim thay phat tu voi Id:" + phatTuId;
        }
        if (baiVietOptional.isEmpty()) {
            return "Khong tim thay bai viet voi Id:" + baiVietId;
        }
        PhatTu phatTu = phatTuOptional.get();
        BaiViet baiViet = baiVietOptional.get();
        for (NguoiDungThichBaiViet nguoiDungThichBaiViet: list) {
            if (phatTuId == phatTu.getId() && baiVietId == baiViet.getId()) {
                phatTuRepo.save(phatTu);
                baiVietRepo.save(baiViet);
                nguoiDungThichBaiViet.setPhatTu(phatTu);
                nguoiDungThichBaiViet.setBaiViet(baiViet);
                nguoiDungThichBaiVietRepo.save(nguoiDungThichBaiViet);
            }
            else {
                return "Danh sach nay khong thoa man";
            }
        }
        return "Them thanh cong";
    }

    @Override
    public NguoiDungThichBaiViet xoaNguoiDungThichBaiViet(int nguoiDungThichBaiVietId) {
        Optional<NguoiDungThichBaiViet> nguoiDung = nguoiDungThichBaiVietRepo.findById(nguoiDungThichBaiVietId);
        if (nguoiDung.isEmpty()) {
            return null;
        }
        NguoiDungThichBaiViet nguoiDungThichBaiViet = nguoiDung.get();
        BaiViet baiViet = nguoiDungThichBaiViet.getBaiViet();
        if (baiViet != null) {
            baiViet.setSoLuotThich(baiViet.getSoLuotThich() - 1);
            baiVietRepo.save(baiViet);
        }
        nguoiDungThichBaiVietRepo.delete(nguoiDungThichBaiViet);
        nguoiDungThichBaiViet.setDaXoa(true);
//        nguoiDungThichBaiVietRepo.save(nguoiDungThichBaiViet);
        return nguoiDungThichBaiViet;
    }

    @Override
    public List<NguoiDungThichBaiViet> getAllNguoiDungThichBaiViet() {
        return nguoiDungThichBaiVietRepo.findAll();
    }
}
