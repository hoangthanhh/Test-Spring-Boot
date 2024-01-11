package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ThemNguoiDungThichBaiVietRequest {
    private LocalDate thoiGianThich;
    private Integer phatTuId;
    private Boolean daXoa;
}
