package SpringBoot.Test.service;

import SpringBoot.Test.entity.LoaiBaiViet;
import SpringBoot.Test.payload.DataRequest.ThemLoaiBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.LoaiBaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;

public interface ILoaiBaiViet {
    ResponseObject<LoaiBaiVietDTO> themLoaiBaiVietRequest(ThemLoaiBaiVietRequest request);
    public LoaiBaiViet suaLoaiBaiViet(LoaiBaiViet loaiBaiVietSua);
}
