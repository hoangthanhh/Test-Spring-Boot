package SpringBoot.Test.service;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.payload.DataRequest.ThemBinhLuanBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemChuaRequest;
import SpringBoot.Test.payload.DataResponse.BinhLuanBaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface IBinhLuanBaiViet {
    ResponseObject<BinhLuanBaiVietDTO> themBinhLuanBaiVietRequest(ThemBinhLuanBaiVietRequest request);
    public BinhLuanBaiViet suaBinhLuanBaiViet(BinhLuanBaiViet binhLuanBaiVietSua);
    public String xoaBinhLuanBaiViet(int binhLuanBaiVietId);
    public BinhLuanBaiViet getBinhLuanBaiViet(int binhLuanBaiVietId);
};
