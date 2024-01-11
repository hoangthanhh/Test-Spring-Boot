package SpringBoot.Test.service;

import SpringBoot.Test.entity.TrangThaiBaiViet;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiDonRequest;
import SpringBoot.Test.payload.DataResponse.TrangThaiBaiVietDTO;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface ITrangThaiBaiViet {
    ResponseObject<TrangThaiBaiVietDTO> themTrangThaiBaiVietRequest(ThemTrangThaiBaiVietRequest request);
    public TrangThaiBaiViet suaTrangThaiBaiViet(TrangThaiBaiViet trangThaiBaiVietSua);
}
