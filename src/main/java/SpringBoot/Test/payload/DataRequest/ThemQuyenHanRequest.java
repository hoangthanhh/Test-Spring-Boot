package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

import java.util.List;

@Data
public class ThemQuyenHanRequest {
    private String tenQuyenHan;
    private List<ThemPhatTuRequest> phatTus;
}
