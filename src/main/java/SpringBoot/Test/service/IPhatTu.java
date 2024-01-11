package SpringBoot.Test.service;

import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.payload.DataRequest.ThemBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemPhatTuRequest;
import SpringBoot.Test.payload.DataResponse.PhatTuDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface IPhatTu {
    ResponseObject<PhatTuDTO> themPhatTuRequest(ThemPhatTuRequest request);
    public PhatTu suaPhatTu(PhatTuDTO phatTuSua);
}
