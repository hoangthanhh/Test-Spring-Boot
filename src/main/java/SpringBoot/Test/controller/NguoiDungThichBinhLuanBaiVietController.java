package SpringBoot.Test.controller;

import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import SpringBoot.Test.service.NguoiDungThichBinhLuanBaiVietService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "apinguoidungthichblbaiviet")
public class NguoiDungThichBinhLuanBaiVietController {
    @Autowired
    private NguoiDungThichBinhLuanBaiVietService nguoiDungThichBinhLuanBaiVietService;
    @PostMapping(value = "themnguoidungthichblbaiviet")
    public String themListNguoiDungThichBLBaiViet(@RequestBody List<NguoiDungThichBinhLuanBaiViet> list, @RequestParam int phatTuId, @RequestParam int binhLuanBaiVietId) {
        return nguoiDungThichBinhLuanBaiVietService.themListNguoiDungThichBL(list, phatTuId, binhLuanBaiVietId);
    }
    @PutMapping(value = "suanguoidungthichblbaiviet")
    public NguoiDungThichBinhLuanBaiViet suaNguoiDungThichBinhLuanBaiViet(@RequestBody String nguoiDungSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        NguoiDungThichBinhLuanBaiViet nguoiDung = gson.fromJson(nguoiDungSua, NguoiDungThichBinhLuanBaiViet.class);
        return nguoiDungThichBinhLuanBaiVietService.suaNguoiDungThichBLBaiViet(nguoiDung);
    }
    @DeleteMapping(value = "xoanguoidungthichblbaiviet")
    public NguoiDungThichBinhLuanBaiViet xoaNguoiDungThichBinhLuanBaiViet(@RequestParam int nguoiDungThichBinhLuanBaiVietId) {
        return nguoiDungThichBinhLuanBaiVietService.xoaNguoiDungThichBLBaiViet(nguoiDungThichBinhLuanBaiVietId);
    }
    @GetMapping(value = "laynguoidungthichblbaiviet")
    public List<NguoiDungThichBinhLuanBaiViet> getAllNguoiDungThichBinhLuanBaiViet() {
        return nguoiDungThichBinhLuanBaiVietService.getAllNguoiDungThichBinhLuanBaiViet();
    }
}
