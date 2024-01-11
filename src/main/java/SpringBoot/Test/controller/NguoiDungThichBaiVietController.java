package SpringBoot.Test.controller;

import SpringBoot.Test.entity.NguoiDungThichBaiViet;
import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import SpringBoot.Test.service.NguoiDungThichBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "apinguoidungthichbv")
public class NguoiDungThichBaiVietController {
    @Autowired
    private NguoiDungThichBaiVietService nguoiDungThichBaiVietService;
    @PostMapping(value = "themnguoidungthichbaiviet")
    public String themListNguoiDungThichBaiViet(@RequestBody List<NguoiDungThichBaiViet> list, @RequestParam int phatTuId, @RequestParam int baiVietId) {
        return nguoiDungThichBaiVietService.themListNguoiDungThichBaiViet(list, phatTuId, baiVietId);
    }
    @DeleteMapping(value = "xoanguoidungthichbaiviet")
    public NguoiDungThichBaiViet xoaNguoiDungThichBaiViet(@RequestParam int nguoiDungThichBaiVietId) {
        return nguoiDungThichBaiVietService.xoaNguoiDungThichBaiViet(nguoiDungThichBaiVietId);
    }
    @GetMapping(value = "laydsnguoidungthichbaiviet")
    public List<NguoiDungThichBaiViet> getAllNguoiDungThichBaiViet() {
        return nguoiDungThichBaiVietService.getAllNguoiDungThichBaiViet();
    }
}
