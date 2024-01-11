package SpringBoot.Test.service;

import SpringBoot.Test.entity.QuyenHan;
import SpringBoot.Test.payload.DataRequest.ThemQuyenHanRequest;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiDonRequest;
import SpringBoot.Test.payload.DataResponse.QuyenHanDTO;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface IQuyenHan {
    ResponseObject<QuyenHanDTO> themQuyenHanRequest(ThemQuyenHanRequest request);
    public QuyenHan suaQuyenHan(QuyenHan quyenHanSua);
}
