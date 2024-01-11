package SpringBoot.Test.controller;

import SpringBoot.Test.entity.TrangThaiBaiViet;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.TrangThaiBaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.TrangThaiBaiVietService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apitrangthaibaiviet")
public class TrangThaiBaiVietController {
    @Autowired
    private TrangThaiBaiVietService trangThaiBaiVietService;

    @PostMapping(value = "themtrangthaibaivietrequest")
    public ResponseObject<TrangThaiBaiVietDTO> themTrangThaiBaiVietRequest(@RequestBody ThemTrangThaiBaiVietRequest request){
        return trangThaiBaiVietService.themTrangThaiBaiVietRequest(request);
    }

    @PutMapping(value = "suatrangthaibaiviet")
    public TrangThaiBaiViet suaTrangThaiBaiViet(@RequestBody String trangThaiBaiVietSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        TrangThaiBaiViet trangThaiBaiViet = gson.fromJson(trangThaiBaiVietSua, TrangThaiBaiViet.class);
        return trangThaiBaiVietService.suaTrangThaiBaiViet(trangThaiBaiViet);
    }
}
