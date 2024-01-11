package SpringBoot.Test.payload.DataRequest;

import lombok.Data;

@Data
public class ThemPhatTuDaoTrangRequest {
    private Boolean daThamGia;
    private String lyDoKhongThamGia;
    private Integer phatTuId;
}
