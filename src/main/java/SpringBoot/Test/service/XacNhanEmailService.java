package SpringBoot.Test.service;

import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.XacNhanEmail;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.XacNhanEmailRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class XacNhanEmailService implements IXacNhanEmail{
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    XacNhanEmailRepo xacNhanEmailRepo;

    @Override
    public String themListXacNhanEmail(List<XacNhanEmail> list, int phatTuId) {
        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuId);
        if (phatTuOptional.isEmpty()) {
            return "Khong tim thay phat tu voi Id:" + phatTuId;
        }
        PhatTu phatTu = phatTuOptional.get();
        for (XacNhanEmail xacNhanEmail: list) {
            if (phatTu.getId() == phatTuId) {
                phatTuRepo.save(phatTu);
                xacNhanEmail.setPhatTu(phatTu);
                xacNhanEmailRepo.save(xacNhanEmail);
            }
            else {
                return "Chi tiet nay khong thoa man";
            }
        }
        return "Them thanh cong";
    }

    @Override
    public XacNhanEmail suaXacNhanEmail(XacNhanEmail xacNhanEmailSua) {
        Optional<XacNhanEmail> xacNhan = xacNhanEmailRepo.findById(xacNhanEmailSua.getId());
        if (xacNhan.isEmpty()) {
            return null;
        }
        XacNhanEmail xacNhanEmail = xacNhan.get();
        xacNhanEmail.setThoiGianHetHan(LocalDate.now());
        xacNhanEmail.setMaXacNhan(xacNhanEmailSua.getMaXacNhan());
        xacNhanEmail.setDaXacNhan(true);
        xacNhanEmail.setPhatTu(xacNhanEmailSua.getPhatTu());
        xacNhanEmailRepo.save(xacNhanEmail);
        return xacNhanEmail;
    }
}
