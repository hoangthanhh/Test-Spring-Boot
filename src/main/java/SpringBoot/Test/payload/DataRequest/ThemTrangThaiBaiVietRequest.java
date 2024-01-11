package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.util.List;
@Data
public class ThemTrangThaiBaiVietRequest {
    private String tenTrangThai;
    private List<ThemBaiVietRequest> baiViets;
}
