package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class BaiVietDTO {
    private Integer Id;
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
    private String tenTaiKhoan;
    private String tenTrangThai;
    private String tenLoai;
    private Set<BinhLuanBaiVietDTO> binhLuanBaiViets;
    private Set<NguoiDungThichBaiVietDTO> nguoiDungThichBaiViets;
}
