package SpringBoot.Test.controller;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.payload.DataRequest.ThemPhatTuRequest;
import SpringBoot.Test.payload.DataResponse.PhatTuDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.service.PhatTuService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apiphattu")
public class PhatTuController {
    @Autowired
    private PhatTuService phatTuService;
    @PostMapping(value = "themphatturequest")
    public ResponseObject<PhatTuDTO> themHoaDonRequest(@RequestBody ThemPhatTuRequest request){
        return phatTuService.themPhatTuRequest(request);
    }
    @PutMapping(value = "suaphattu")
    public PhatTu suaPhatTu(@RequestBody String phatTuSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        PhatTuDTO phatTu = gson.fromJson(phatTuSua, PhatTuDTO.class);
        return phatTuService.suaPhatTu(phatTu);
    }
}
