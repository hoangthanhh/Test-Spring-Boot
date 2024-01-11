package SpringBoot.Test.controller;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.entity.XacNhanEmail;
import SpringBoot.Test.service.DonDangKyService;
import SpringBoot.Test.service.XacNhanEmailService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
public class XacNhanEmailController {
    @Autowired
    private XacNhanEmailService xacNhanEmailService;
    @PostMapping(value = "themlistxacnhanemail")
    public String themListDonDangKy(@RequestBody List<XacNhanEmail> list, @RequestParam int phatTuId) {
        return xacNhanEmailService.themListXacNhanEmail(list,phatTuId);
    }
    @PutMapping(value = "suaxacnhanemail")
    public XacNhanEmail suaXacNhanEmail(@RequestBody String xacNhanEmailSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        XacNhanEmail xacNhanEmail = gson.fromJson(xacNhanEmailSua, XacNhanEmail.class);
        return xacNhanEmailService.suaXacNhanEmail(xacNhanEmail);
    }
}
