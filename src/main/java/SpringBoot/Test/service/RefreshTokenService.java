package SpringBoot.Test.service;

import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.entity.RefreshToken;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.RefreshTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RefreshTokenService implements IRefreshToken{
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Override
    public String themListRefreshToken(List<RefreshToken> list, int phatTuId) {
        Optional<PhatTu> phatTuOptional = phatTuRepo.findById(phatTuId);
        if (phatTuOptional.isEmpty()) {
            return "Khong tim thay phat tu voi Id:" + phatTuId;
        }
        PhatTu phatTu = phatTuOptional.get();
        for (RefreshToken refreshToken: list) {
            if (phatTuId == phatTu.getId()) {
                phatTuRepo.save(phatTu);
                refreshToken.setPhatTu(phatTu);
                refreshTokenRepo.save(refreshToken);
            }
            else {
                return "Chi tiet nay khong thoa man";
            }
        }
        return "Them thanh cong";
    }

    @Override
    public RefreshToken suaRefreshToken(RefreshToken refreshTokenSua) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepo.findById(refreshTokenSua.getId());
        if (refreshTokenOptional.isEmpty()) {
            return null;
        }
        RefreshToken refreshToken = refreshTokenOptional.get();
        refreshToken.setToken(refreshTokenSua.getToken());
        refreshToken.setThoiGianHetHan(LocalDate.now());
        refreshToken.setPhatTu(refreshTokenSua.getPhatTu());
        refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }
}
