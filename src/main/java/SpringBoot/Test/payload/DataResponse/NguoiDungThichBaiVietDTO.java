package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NguoiDungThichBaiVietDTO {
    private Integer nguoiDungThichBaiVietId;
    private LocalDate thoiGianThich;
    private Boolean daXoa;
    private String tenTaiKhoan;
    private Integer loaiBaiVietId;
}
