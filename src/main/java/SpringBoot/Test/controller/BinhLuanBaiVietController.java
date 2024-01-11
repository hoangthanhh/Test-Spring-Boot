package SpringBoot.Test.controller;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.payload.DataRequest.ThemBinhLuanBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemChuaRequest;
import SpringBoot.Test.payload.DataResponse.BinhLuanBaiVietDTO;
import SpringBoot.Test.payload.DataResponse.ChuaDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.BinhLuanBaiVietService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apibinhluanbaiviet")
public class BinhLuanBaiVietController {
    @Autowired
    private BinhLuanBaiVietService binhLuanBaiVietService;
    @PostMapping(value = "thembinhluanbaivietrequest")
    public ResponseObject<BinhLuanBaiVietDTO> themBinhLuanBaiVietRequest(@RequestBody ThemBinhLuanBaiVietRequest request){
        return binhLuanBaiVietService.themBinhLuanBaiVietRequest(request);
    }
    @PutMapping(value = "suabinhluan")
    public BinhLuanBaiViet suaBinhLuanBaiViet(@RequestBody String binhLuanBaiVietSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        BinhLuanBaiViet binhLuanBaiViet = gson.fromJson(binhLuanBaiVietSua, BinhLuanBaiViet.class);
        return binhLuanBaiVietService.suaBinhLuanBaiViet(binhLuanBaiViet);
    }
    @DeleteMapping(value = "xoabinhluan")
    public String xoaBinhLuanBaiViet(@RequestParam int binhLuanBaiVietId) {
        return binhLuanBaiVietService.xoaBinhLuanBaiViet(binhLuanBaiVietId);
    }
    @GetMapping(value = "laybinhluanbaiviet")
    public BinhLuanBaiViet getBinhLuanBaiViet(@RequestParam int binhLuanBaiVietId) {
        return binhLuanBaiVietService.getBinhLuanBaiViet(binhLuanBaiVietId);
    }
}
