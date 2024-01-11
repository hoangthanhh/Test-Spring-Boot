package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RefreshTokenDTO {
    private Integer Id;
    private String token;
    private LocalDate thoiGianHetHan;
}
