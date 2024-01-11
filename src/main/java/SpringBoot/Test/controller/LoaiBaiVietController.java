package SpringBoot.Test.controller;

import SpringBoot.Test.entity.LoaiBaiViet;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataRequest.ThemLoaiBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.LoaiBaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.LoaiBaiVietService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apiloaibaiviet")
public class LoaiBaiVietController {
    @Autowired
    private LoaiBaiVietService loaiBaiVietService;

    @PostMapping(value = "themloaibaivietrequest")
    public ResponseObject<LoaiBaiVietDTO> themLoaiBaiVietRequest(@RequestBody ThemLoaiBaiVietRequest request) {
        return loaiBaiVietService.themLoaiBaiVietRequest(request);
    }

    @PutMapping(value = "sualoaibaiviet")
    public LoaiBaiViet suaLoaiBaiViet(@RequestBody String loaiBaiVietSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        LoaiBaiViet loaiBaiViet = gson.fromJson(loaiBaiVietSua, LoaiBaiViet.class);
        return loaiBaiVietService.suaLoaiBaiViet(loaiBaiViet);
    }
}
