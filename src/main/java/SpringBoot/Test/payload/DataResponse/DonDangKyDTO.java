package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonDangKyDTO {
    private Integer Id;
    private LocalDate ngayGuiDon;
    private LocalDate ngayXuLy;
    private Integer nguoiXuLy;
    private Boolean daKetThuc; // them trang thai don
    private String tenTaiKhoan; // them trang thai don, dao trang
    private String tenTrangThai; //them dao trang
}
