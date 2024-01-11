package SpringBoot.Test.payload.DataResponse;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

@Data
public class XacNhanEmailDTO {
    private Integer Id;
    private LocalDate thoiGianHetHan;
    private String maXacNhan;
    private Boolean daXacNhan;
}
