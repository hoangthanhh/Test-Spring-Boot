package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.util.List;

@Data
public class ThemLoaiBaiVietRequest {
    private String tenLoai;
    private List<ThemBaiVietRequest> baiViets;
}
