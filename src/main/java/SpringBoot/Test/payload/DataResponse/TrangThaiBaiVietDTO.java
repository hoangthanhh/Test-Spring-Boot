package SpringBoot.Test.payload.DataResponse;

import SpringBoot.Test.entity.BaiViet;
import lombok.Data;

import java.util.Set;

@Data
public class TrangThaiBaiVietDTO {
    private String tenTrangThai;
    private Set<BaiVietDTO> baiViets;
}
