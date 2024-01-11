package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ChuaDTO {
    private String tenChua;
    private String diaChi;
    private LocalDate ngayThanhLap;
    private String nguoiTruTri;
    private Set<PhatTuDTO> phatTus;
}
