package SpringBoot.Test.payload.DataResponse;

import SpringBoot.Test.entity.BaiViet;
import lombok.Data;

import java.util.Set;

@Data
public class LoaiBaiVietDTO {
    private String tenLoai;
    private Set<BaiVietDTO> baiViets;
}
