package SpringBoot.Test.service;

import SpringBoot.Test.entity.RefreshToken;

import java.util.List;

public interface IRefreshToken {
    public String themListRefreshToken(List<RefreshToken> list, int phatTuId);
    public RefreshToken suaRefreshToken(RefreshToken refreshTokenSua);
}
