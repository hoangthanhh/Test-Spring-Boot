package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ThemDonDangKyRequest {
    private LocalDate ngayGuiDon;
    private LocalDate ngayXuLy;
    private Integer nguoiXuLy;
    private Integer daoTrangId;
    private Integer phatTuId;
    private Integer trangThaiDonId; // them dao trang
}
