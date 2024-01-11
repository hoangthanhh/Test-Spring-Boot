package SpringBoot.Test.service;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.payload.DataRequest.ThemChuaRequest;
import SpringBoot.Test.payload.DataRequest.ThemDaoTrangRequest;
import SpringBoot.Test.payload.DataResponse.ChuaDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface IChua {
    ResponseObject<ChuaDTO> themChuaRequest(ThemChuaRequest request);
    public Chua suaChua(Chua chuaSua);
}
