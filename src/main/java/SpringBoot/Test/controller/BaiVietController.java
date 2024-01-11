package SpringBoot.Test.controller;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.payload.DataRequest.ThemBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.BaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.BaiVietService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apibaiviet")
public class BaiVietController {
    @Autowired
    private BaiVietService baiVietService;
    @PostMapping(value = "thembaivietrequest")
    public ResponseObject<BaiVietDTO> themBinhLuanBaiVietRequest(@RequestBody ThemBaiVietRequest request){
        return baiVietService.themBaiVietRequest(request);
    }
    @PutMapping(value = "suabaiviet")
    public BaiViet suaBaiViet(@RequestBody String baiVietSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        BaiViet baiViet = gson.fromJson(baiVietSua, BaiViet.class);
        return baiVietService.suaBaiViet(baiViet);
    }
    @DeleteMapping(value = "xoabaiviet")
    public String xoaBaiViet(@RequestParam int baiVietId) {
        return baiVietService.xoaBaiViet(baiVietId);
    }
    @GetMapping(value = "laybaiviet")
    public BaiViet getBaiViet(@RequestParam int baiVietId) {
        return baiVietService.getBaiViet(baiVietId);
    }
}
