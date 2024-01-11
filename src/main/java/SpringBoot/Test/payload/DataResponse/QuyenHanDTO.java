package SpringBoot.Test.payload.DataResponse;

import lombok.Data;

import java.util.Set;

@Data
public class QuyenHanDTO {
    private String tenQuyenHan;
    private Set<PhatTuDTO> phatTus;
}
