package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class ThemChuaRequest {
    private String tenChua;
    private String diaChi;
    private LocalDate ngayThanhLap;
    private String nguoiTruTri;
    private List<ThemPhatTuRequest> phatTus;
}
