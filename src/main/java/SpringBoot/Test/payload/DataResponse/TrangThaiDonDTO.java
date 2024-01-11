package SpringBoot.Test.payload.DataResponse;

import SpringBoot.Test.entity.DonDangKy;
import lombok.Data;

import java.util.Set;

@Data
public class TrangThaiDonDTO {
    private String tenTrangThai;
    private Set<DonDangKyDTO> donDangKys;
}
