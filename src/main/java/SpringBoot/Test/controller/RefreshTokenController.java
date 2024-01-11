package SpringBoot.Test.controller;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.RefreshToken;
import SpringBoot.Test.service.RefreshTokenService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "apirefreshtoken")
public class RefreshTokenController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @PostMapping(value = "themlistrefreshtoken")
    public String themListRefreshToken(@RequestBody List<RefreshToken> list, @RequestParam int phatTuId) {
        return refreshTokenService.themListRefreshToken(list,phatTuId);
    }
    @PutMapping(value = "suarefreshtoken")
    public RefreshToken suaRefreshToken(@RequestBody String refreshTokenSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        RefreshToken refreshToken = gson.fromJson(refreshTokenSua, RefreshToken.class);
        return refreshTokenService.suaRefreshToken(refreshToken);
    }
}
