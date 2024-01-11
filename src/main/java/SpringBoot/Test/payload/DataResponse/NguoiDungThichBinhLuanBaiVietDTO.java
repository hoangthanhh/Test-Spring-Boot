package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NguoiDungThichBinhLuanBaiVietDTO {
    private Integer nguoiDungThichBinhLuanBaiVietId;
    private LocalDate thoiGianLike;
    private Boolean daXoa;
    private String tenTaiKhoan;
}
