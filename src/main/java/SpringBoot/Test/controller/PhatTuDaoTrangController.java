package SpringBoot.Test.controller;

import SpringBoot.Test.entity.Chua;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import SpringBoot.Test.service.PhatTuDaoTrangService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "apiphattudaotrang")
public class PhatTuDaoTrangController {
    @Autowired
    private PhatTuDaoTrangService phatTuDaoTrangService;
    @PostMapping(value = "themlistphattudaotrang")
    public String themListPhatTuDaoTrang(@RequestBody List<PhatTuDaoTrang> list, @RequestParam int phatTuId, @RequestParam int daoTrangId) {
        return phatTuDaoTrangService.themListPhatTuDaoTrang(list, phatTuId, daoTrangId);
    }
    @PutMapping(value = "suaphattudaotrang")
    public PhatTuDaoTrang suaPhatTuDaoTrang(@RequestBody String phatTuDaoTrangSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        PhatTuDaoTrang phatTuDaoTrang = gson.fromJson(phatTuDaoTrangSua, PhatTuDaoTrang.class);
        return phatTuDaoTrangService.suaPhatTuDaoTrang(phatTuDaoTrang);
    }
}
