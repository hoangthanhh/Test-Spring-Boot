package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ThemXacNhanEmailRequest {
    private LocalDate thoiGianHetHan;
    private String maXacNhan;
    private Boolean daXacNhan;
}
