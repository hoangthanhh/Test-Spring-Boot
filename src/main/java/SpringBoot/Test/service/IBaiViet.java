package SpringBoot.Test.service;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.payload.DataRequest.ThemBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemBinhLuanBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.BaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import org.springframework.stereotype.Service;

public interface IBaiViet {
    ResponseObject<BaiVietDTO> themBaiVietRequest(ThemBaiVietRequest request);
    public BaiViet suaBaiViet(BaiViet baiVietSua);
    public String xoaBaiViet(int baiVietId);
    public BaiViet getBaiViet(int baiVietId);
}
