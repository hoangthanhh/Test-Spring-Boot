package SpringBoot.Test.payload.DataRequest;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ThemPhatTuRequest {
    private String tenTaiKhoan;
    private String anhChup;
    private Boolean daHoanTuc;
    private String email;
    private String gioiTinh;
    private LocalDate ngayCapNhat;
    private LocalDate ngayHoanTuc;
    private LocalDate ngaySinh;
    private String matKhau;
    private String phapDanh;
    private String soDienThoai;
    private Integer quyenHanId;
    private Integer chuaId;
    private List<ThemXacNhanEmailRequest> xacNhanEmails;
    private List<ThemRefreshTokenRequest> refreshTokens;
    private List<ThemBaiVietRequest> baiViets;
    private List<BinhLuanBaiViet> binhLuanBaiViets;
}
