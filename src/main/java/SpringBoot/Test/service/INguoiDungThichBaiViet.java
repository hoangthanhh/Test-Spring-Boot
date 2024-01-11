package SpringBoot.Test.service;

import SpringBoot.Test.entity.NguoiDungThichBaiViet;

import java.util.List;

public interface INguoiDungThichBaiViet {
    public String themListNguoiDungThichBaiViet(List<NguoiDungThichBaiViet> list, int phatTuId, int baiVietId);
    public NguoiDungThichBaiViet xoaNguoiDungThichBaiViet(int nguoiDungThichBaiVietId);
    public List<NguoiDungThichBaiViet> getAllNguoiDungThichBaiViet();
}
