package SpringBoot.Test.service;

import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiDonRequest;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface ITrangThaiDon {
    ResponseObject<TrangThaiDonDTO> themTrangThaiDonRequest(ThemTrangThaiDonRequest request);
    public TrangThaiDon suaTrangThaiDon(TrangThaiDon trangThaiDonSua);
}
