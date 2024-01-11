package SpringBoot.Test.payload.DataRequest;

import SpringBoot.Test.entity.DonDangKy;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ThemTrangThaiDonRequest {
    private String tenTrangThai;
    private List<ThemDonDangKyRequest> donDangKys;
}
