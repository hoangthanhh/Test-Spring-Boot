package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ThemRefreshTokenRequest {
    private String token;
    private LocalDate thoiGianHetHan;
}
