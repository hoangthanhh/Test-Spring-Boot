package SpringBoot.Test.controller;

import SpringBoot.Test.entity.QuyenHan;
import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.payload.DataRequest.ThemQuyenHanRequest;
import SpringBoot.Test.payload.DataResponse.QuyenHanDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.service.QuyenHanService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "apiquyenhan")
public class QuyenHanController {
    @Autowired
    private QuyenHanService quyenHanService;

    @PostMapping(value = "themquyenhanrequest")
    public ResponseObject<QuyenHanDTO> themQuyenHanRequest(@RequestBody ThemQuyenHanRequest request){
        return quyenHanService.themQuyenHanRequest(request);
    }
    @PutMapping(value = "suaquyenhan")
    public QuyenHan suaQuyenHan(@RequestBody String quyenHanSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        QuyenHan quyenHan = gson.fromJson(quyenHanSua, QuyenHan.class);
        return quyenHanService.suaQuyenHan(quyenHan);
    }
}
