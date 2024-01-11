package SpringBoot.Test.payload.DataResponse;

import SpringBoot.Test.entity.*;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.service.XacNhanEmailService;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PhatTuDTO {
    private Integer Id;
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
    private String tenChua;
    private String tenQuyenHan;
    private Set<RefreshTokenDTO> refreshTokens;
    private Set<XacNhanEmailDTO> xacNhanEmails;
}
