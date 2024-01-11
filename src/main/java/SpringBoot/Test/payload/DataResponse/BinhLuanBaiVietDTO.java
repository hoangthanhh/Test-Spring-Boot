package SpringBoot.Test.payload.DataResponse;

import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class BinhLuanBaiVietDTO {
    private Integer binhLuanBaiVietId;
    private String binhLuan;
    private Integer soLuotThich;
    private LocalDate thoiGianTao;
    private LocalDate thoiGianCapNhat;
    private LocalDate thoiGianXoa;
    private Boolean daXoa;
    private String tenTaiKhoan;
    private Integer loaiBaiVietId;
    private Set<NguoiDungThichBinhLuanBaiVietDTO> nguoiDungThichBinhLuanBaiViets;
}
