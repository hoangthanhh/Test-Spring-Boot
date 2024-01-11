package SpringBoot.Test.service;

import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;

import javax.swing.plaf.PanelUI;
import java.util.List;

public interface INguoiDungThichBinhLuan {
    public String themListNguoiDungThichBL(List<NguoiDungThichBinhLuanBaiViet> list, int phatTuId, int binhLuanBaiVietId);
    public NguoiDungThichBinhLuanBaiViet suaNguoiDungThichBLBaiViet(NguoiDungThichBinhLuanBaiViet nguoiDungSua);
    public NguoiDungThichBinhLuanBaiViet xoaNguoiDungThichBLBaiViet(int nguoiDungThichBLBaiVietId);
    public List<NguoiDungThichBinhLuanBaiViet> getAllNguoiDungThichBinhLuanBaiViet();
}
