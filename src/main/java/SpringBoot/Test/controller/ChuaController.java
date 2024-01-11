package SpringBoot.Test.controller;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.entity.QuyenHan;
import SpringBoot.Test.payload.DataRequest.ThemChuaRequest;
import SpringBoot.Test.payload.DataResponse.ChuaDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.ChuaService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apichua")
public class ChuaController {
    @Autowired
    private ChuaService chuaService;

    @PostMapping(value = "themchuarequest")
    public ResponseObject<ChuaDTO> themChuaRequest(@RequestBody ThemChuaRequest request){
        return chuaService.themChuaRequest(request);
    }
    @PutMapping(value = "suachua")
    public Chua suaChua(@RequestBody String chuaSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Chua chua = gson.fromJson(chuaSua, Chua.class);
        return chuaService.suaChua(chua);
    }
}
