package SpringBoot.Test.service;

import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.payload.DataRequest.ThemDaoTrangRequest;
import SpringBoot.Test.payload.DataRequest.ThemQuyenHanRequest;
import SpringBoot.Test.payload.DataResponse.DaoTrangDTO;
import SpringBoot.Test.payload.DataResponse.QuyenHanDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface IDaoTrang {
    ResponseObject<DaoTrangDTO> themDaoTrangRequest(ThemDaoTrangRequest request);
    public DaoTrang suaDaoTrang(DaoTrang daoTrangSua);
}
