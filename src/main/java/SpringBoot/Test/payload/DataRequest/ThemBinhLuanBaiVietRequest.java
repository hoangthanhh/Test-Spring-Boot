package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ThemBinhLuanBaiVietRequest {
    private String binhLuan;
    private Integer baiVietId;
    private Integer phatTuId;
    private Integer soLuotThich;
    private LocalDate thoiGianTao;
    private Boolean daXoa;
    private List<ThemNguoiDungThichBinhLuanBaiVietRequest> nguoiDungThichBinhLuanBaiViets;
}
