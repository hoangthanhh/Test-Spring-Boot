package SpringBoot.Test.payload.DataResponse;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DaoTrangDTO {
    private Boolean daKetThuc;
    private String noiDung;
    private String noiToChuc;
    private Integer soThanhVienThamGia;
    private LocalDate thoiGianBatDau;
    private Integer nguoiTruTri;
    private Set<DonDangKyDTO> donDangKys;
    private Set<PhatTuDaoTrangDTO> phatTuDaoTrangs;
}
