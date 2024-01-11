package SpringBoot.Test.controller;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.payload.DataResponse.DonDangKyDTO;
import SpringBoot.Test.payload.DataResponse.PhatTuDTO;
import SpringBoot.Test.repository.DonDangKyRepo;
import SpringBoot.Test.service.DonDangKyService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "apidondangky")
public class DonDangKyController {
    @Autowired
    private DonDangKyService donDangKyService;
    @PostMapping(value = "themlistdondangky")
    public String themListDonDangKy(@RequestBody List<DonDangKy> list, @RequestParam int trangThaiDonId) {
        return donDangKyService.themListDonDangKy(list,trangThaiDonId);
    }
    @PutMapping(value = "suadondangky")
    public DonDangKy suaDonDangKy(@RequestBody String donDangKySua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        DonDangKy donDangKy = gson.fromJson(donDangKySua, DonDangKy.class);
        return donDangKyService.suaDonDangKy(donDangKy);
    }
}
