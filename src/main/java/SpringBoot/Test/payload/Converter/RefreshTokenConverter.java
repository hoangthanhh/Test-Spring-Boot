package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.RefreshToken;
import SpringBoot.Test.payload.DataResponse.RefreshTokenDTO;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenConverter {
    public RefreshTokenDTO entityToDTO(RefreshToken refreshToken) {
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
        refreshTokenDTO.setId(refreshToken.getId());
        refreshTokenDTO.setToken(refreshToken.getToken());
        refreshTokenDTO.setThoiGianHetHan(refreshToken.getThoiGianHetHan());
        return refreshTokenDTO;
    }
}
