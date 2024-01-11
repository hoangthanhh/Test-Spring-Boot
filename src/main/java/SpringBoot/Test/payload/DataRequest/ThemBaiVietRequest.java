package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ThemBaiVietRequest {
    private String tieuDe;
    private String moTa;
    private String noiDung;
    private Integer nguoiDuyetBaiVietId;
    private Integer soLuotThich;
    private Integer soLuotBinhLuan;
    private LocalDate thoiGianDang;
    private LocalDate thoiGianCapNhat;
    private LocalDate thoiGianXoa;
    private Boolean daXoa;
    private Integer phatTuId;
    private Integer trangThaiBaiVietId;
    private Integer loaiBaiVietId;
    private List<ThemBinhLuanBaiVietRequest> binhLuanBaiViets;
    private List<ThemNguoiDungThichBaiVietRequest> nguoiDungThichBaiViets;
}
