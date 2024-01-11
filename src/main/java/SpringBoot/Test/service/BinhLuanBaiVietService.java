package SpringBoot.Test.service;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import SpringBoot.Test.payload.Converter.BinhLuanBaiVietConverter;
import SpringBoot.Test.payload.DataRequest.ThemBinhLuanBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemNguoiDungThichBinhLuanBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.BinhLuanBaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.BinhLuanBaiVietRepo;
import SpringBoot.Test.repository.NguoiDungThichBinhLuanBaiVietRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BinhLuanBaiVietService implements IBinhLuanBaiViet{
    @Autowired
    private BinhLuanBaiVietRepo binhLuanBaiVietRepo;
    @Autowired
    private NguoiDungThichBinhLuanBaiVietRepo nguoiDungThichBinhLuanBaiVietRepo;
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    private final BinhLuanBaiVietConverter _converter;
    private final ResponseObject<BinhLuanBaiVietDTO> _responseObject;
    public BinhLuanBaiVietService(BinhLuanBaiVietConverter converter, ResponseObject<BinhLuanBaiVietDTO> responseObject) {
        _converter = converter;
        _responseObject = responseObject;
    }

    private Set<NguoiDungThichBinhLuanBaiViet> themListNguoiDungThichBinhLuanBaiViet(int binhLuanBaiVietId, List<ThemNguoiDungThichBinhLuanBaiVietRequest> requests) {
        var binhLuanBaiViet = binhLuanBaiVietRepo.findById(binhLuanBaiVietId);
        if (binhLuanBaiViet.isEmpty()) {
            return null;
        }
        Set<NguoiDungThichBinhLuanBaiViet> set = new HashSet<>();
        for (var request: requests) {
            NguoiDungThichBinhLuanBaiViet nguoi =new NguoiDungThichBinhLuanBaiViet();
            nguoi.setBinhLuanBaiViet(binhLuanBaiViet.get());
//            nguoi.setDaXoa(request.getDaXoa());
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            nguoi.setPhatTu(phatTu);
            nguoi.setThoiGianLike(LocalDate.now());
            set.add(nguoi);
        }
        nguoiDungThichBinhLuanBaiVietRepo.saveAll(set);
        binhLuanBaiViet.get().setNguoiDungThichBinhLuanBaiViets(set);
        binhLuanBaiVietRepo.save(binhLuanBaiViet.get());
        int soLuotThich = binhLuanBaiViet.get().getNguoiDungThichBinhLuanBaiViets().size();
        binhLuanBaiViet.get().setSoLuotThich(soLuotThich);
        binhLuanBaiVietRepo.save(binhLuanBaiViet.get());
        return set;
    }
    @Override
    public ResponseObject<BinhLuanBaiVietDTO> themBinhLuanBaiVietRequest(ThemBinhLuanBaiVietRequest request) {
        var phatTu = phatTuRepo.findById(request.getPhatTuId());
        if (phatTu.isEmpty()) {
            return null;
        }
        var baiViet = baiVietRepo.findById(request.getBaiVietId());
        if (baiViet.isEmpty()) {
            return null;
        }
        BinhLuanBaiViet binhLuanBaiViet = new BinhLuanBaiViet();
        binhLuanBaiViet.setBinhLuan(request.getBinhLuan());
        binhLuanBaiViet.setThoiGianTao(LocalDate.now());
        binhLuanBaiViet.setSoLuotThich(null);
        binhLuanBaiViet.setNguoiDungThichBinhLuanBaiViets(null);
        binhLuanBaiViet.setPhatTu(phatTu.get());
        binhLuanBaiViet.setBaiViet(baiViet.get());
        binhLuanBaiVietRepo.save(binhLuanBaiViet);
        binhLuanBaiViet.setNguoiDungThichBinhLuanBaiViets(themListNguoiDungThichBinhLuanBaiViet(binhLuanBaiViet.getId(), request.getNguoiDungThichBinhLuanBaiViets()));
        return _responseObject.responseSuccess("Them binh luan bai viet thanh cong", _converter.entityToDTO(binhLuanBaiViet));
    }

    @Override
    public BinhLuanBaiViet suaBinhLuanBaiViet(BinhLuanBaiViet binhLuanBaiVietSua) {
        Optional<BinhLuanBaiViet> binhLuanBaiVietOptional = binhLuanBaiVietRepo.findById(binhLuanBaiVietSua.getId());
        if (binhLuanBaiVietOptional.isEmpty()) {
            return null;
        }
        BinhLuanBaiViet binhLuanBaiViet = binhLuanBaiVietOptional.get();
        binhLuanBaiViet.setBinhLuan(binhLuanBaiVietSua.getBinhLuan());
        binhLuanBaiViet.setThoiGianCapNhat(LocalDate.now());
        binhLuanBaiVietRepo.save(binhLuanBaiViet);
        return binhLuanBaiViet;
    }

    @Override
    public String xoaBinhLuanBaiViet(int binhLuanBaiVietId) {
        Optional<BinhLuanBaiViet> binhLuanBaiVietOptional = binhLuanBaiVietRepo.findById(binhLuanBaiVietId);
        if (binhLuanBaiVietOptional.isEmpty()) {
            return null;
        }
        nguoiDungThichBinhLuanBaiVietRepo.findAll().forEach(x -> {
            BinhLuanBaiViet binhLuanBaiViet = x.getBinhLuanBaiViet();
            if (binhLuanBaiViet != null && binhLuanBaiViet.getId() == binhLuanBaiVietId) {
                nguoiDungThichBinhLuanBaiVietRepo.delete(x);
            }
        });
        BinhLuanBaiViet binhLuanBaiViet = binhLuanBaiVietOptional.get();
        binhLuanBaiVietRepo.delete(binhLuanBaiViet);
        binhLuanBaiViet.setThoiGianXoa(LocalDate.now());
        binhLuanBaiViet.setDaXoa(true);
        BaiViet baiViet = binhLuanBaiViet.getBaiViet();
        if (baiViet != null) {
            baiViet.setSoLuotBinhLuan(baiViet.getSoLuotBinhLuan() - 1);
            baiVietRepo.save(baiViet);
        }
        return "Xoa thanh cong";
    }

    @Override
    public BinhLuanBaiViet getBinhLuanBaiViet(int binhLuanBaiVietId) {
        Optional<BinhLuanBaiViet> binhLuanBaiVietOptional = binhLuanBaiVietRepo.findById(binhLuanBaiVietId);
        if (binhLuanBaiVietOptional.isEmpty()) {
            return null;
        }
        BinhLuanBaiViet binhLuanBaiViet = binhLuanBaiVietOptional.get();
        return binhLuanBaiViet;
    }
}
