package SpringBoot.Test.controller;

import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiDonRequest;
import SpringBoot.Test.payload.DataResponse.TrangThaiDonDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.TrangThaiDonService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apitrangthaidon")
public class TrangThaiDonConTroller {
    @Autowired
    private TrangThaiDonService trangThaiDonService;

    @PostMapping(value = "themtrangthaidonrequest")
    public ResponseObject<TrangThaiDonDTO> themTrangThaiDonRequest(@RequestBody ThemTrangThaiDonRequest request){
        return trangThaiDonService.themTrangThaiDonRequest(request);
    }

    @PutMapping(value = "suatrangthaidon")
    public TrangThaiDon suaTrangThaiDon(@RequestBody String trangThaiDonSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        TrangThaiDon trangThaiDon = gson.fromJson(trangThaiDonSua, TrangThaiDon.class);
        return trangThaiDonService.suaTrangThaiDon(trangThaiDon);
    }
}
