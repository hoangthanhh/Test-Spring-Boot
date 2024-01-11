package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ThemDaoTrangRequest {
    private Boolean daKetThuc;
    private String noiDung;
    private String noiToChuc;
    private Integer soThanhVienThamGia;
    private LocalDate thoiGianBatDau;
    private Integer nguoiTruTri;
    private List<ThemDonDangKyRequest> donDangKys;
    private List<ThemPhatTuDaoTrangRequest> phatTuDaoTrangs;
}
