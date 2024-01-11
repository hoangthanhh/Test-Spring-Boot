package SpringBoot.Test.controller;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.payload.DataRequest.ThemDaoTrangRequest;
import SpringBoot.Test.payload.DataResponse.DaoTrangDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.DaoTrangService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apidaotrang")
public class DaoTrangController {
    @Autowired
    private DaoTrangService daoTrangService;

    @PostMapping(value = "themdaotrangrequest")
    public ResponseObject<DaoTrangDTO> themDaoTrangRequest(@RequestBody ThemDaoTrangRequest request){
        return daoTrangService.themDaoTrangRequest(request);
    }
    @PutMapping(value = "suadaotrang")
    public DaoTrang suaDaoTrang(@RequestBody String daoTrangSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        DaoTrang daoTrang = gson.fromJson(daoTrangSua, DaoTrang.class);
        return daoTrangService.suaDaoTrang(daoTrang);
    }
}
