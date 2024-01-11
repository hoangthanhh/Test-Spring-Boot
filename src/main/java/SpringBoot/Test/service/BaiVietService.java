package SpringBoot.Test.service;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.BinhLuanBaiViet;
import SpringBoot.Test.entity.NguoiDungThichBaiViet;
import SpringBoot.Test.payload.Converter.BaiVietConverter;
import SpringBoot.Test.payload.DataRequest.ThemBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemBinhLuanBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemNguoiDungThichBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.BaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BaiVietService implements IBaiViet {
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Autowired
    private BinhLuanBaiVietRepo binhLuanBaiVietRepo;
    @Autowired
    private NguoiDungThichBaiVietRepo nguoiDungThichBaiVietRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    private TrangThaiBaiVietRepo trangThaiBaiVietRepo;
    private final BaiVietConverter _converter;
    private final ResponseObject<BaiVietDTO> _responseObject;
    public BaiVietService(BaiVietConverter converter, ResponseObject<BaiVietDTO> responseObject) {
        _converter = converter;
        _responseObject = responseObject;
    }
    private Set<BinhLuanBaiViet> themListBinhLuanBaiViet(int baiVietId, List<ThemBinhLuanBaiVietRequest> requests) {
        var baiViet = baiVietRepo.findById(baiVietId);
        if (baiViet.isEmpty()) {
            return null;
        }
        Set<BinhLuanBaiViet> set = new HashSet<>();
        for (var request: requests) {
            BinhLuanBaiViet binhLuanBaiViet = new BinhLuanBaiViet();
            binhLuanBaiViet.setBaiViet(baiViet.get());
            binhLuanBaiViet.setBinhLuan(request.getBinhLuan());
            binhLuanBaiViet.setThoiGianTao(LocalDate.now());
            binhLuanBaiViet.setSoLuotThich(request.getSoLuotThich());
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            binhLuanBaiViet.setPhatTu(phatTu);
            set.add(binhLuanBaiViet);
        }
        binhLuanBaiVietRepo.saveAll(set);
        baiViet.get().setBinhLuanBaiViets(set);
        baiVietRepo.save(baiViet.get());
        int soLuotBinhLuan = baiViet.get().getBinhLuanBaiViets().size();
        baiViet.get().setSoLuotBinhLuan(soLuotBinhLuan);
        baiVietRepo.save(baiViet.get());
        return set;
    }
    private Set<NguoiDungThichBaiViet> themListNguoiDungThichBaiViet(int baiVietId, List<ThemNguoiDungThichBaiVietRequest> requests) {
        var baiViet = baiVietRepo.findById(baiVietId);
        if (baiViet.isEmpty()) {
            return null;
        }
        Set<NguoiDungThichBaiViet> set = new HashSet<>();
        for (var request: requests) {
            NguoiDungThichBaiViet nguoi = new NguoiDungThichBaiViet();
            nguoi.setBaiViet(baiViet.get());
            nguoi.setThoiGianThich(LocalDate.now());
            nguoi.setDaXoa(request.getDaXoa());
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            nguoi.setPhatTu(phatTu);
            set.add(nguoi);
        }
        nguoiDungThichBaiVietRepo.saveAll(set);
        baiViet.get().setNguoiDungThichBaiViets(set);
        baiVietRepo.save(baiViet.get());
        int soLuotThich = baiViet.get().getNguoiDungThichBaiViets().size();
        baiViet.get().setSoLuotThich(soLuotThich);
        baiVietRepo.save(baiViet.get());
        return set;
    }

    @Override
    public ResponseObject<BaiVietDTO> themBaiVietRequest(ThemBaiVietRequest request) {
        var loaiBaiViet = loaiBaiVietRepo.findById(request.getLoaiBaiVietId());
        if (loaiBaiViet.isEmpty()) {
            return null;
        }
        var phatTu = phatTuRepo.findById(request.getPhatTuId());
        if (phatTu.isEmpty()) {
            return null;
        }
        var trangThaiBaiViet = trangThaiBaiVietRepo.findById(request.getTrangThaiBaiVietId());
        if (trangThaiBaiViet.isEmpty()) {
            return null;
        }
        BaiViet baiViet = new BaiViet();
        baiViet.setTieuDe(request.getTieuDe());
        baiViet.setMoTa(request.getMoTa());
        baiViet.setNoiDung(request.getNoiDung());
        baiViet.setNguoiDuyetBaiVietId(request.getNguoiDuyetBaiVietId());
        baiViet.setSoLuotThich(null);
        baiViet.setSoLuotBinhLuan(null);
        baiViet.setThoiGianDang(LocalDate.now());
        baiViet.setLoaiBaiViet(loaiBaiViet.get());
        baiViet.setPhatTu(phatTu.get());
        baiViet.setTrangThaiBaiViet(trangThaiBaiViet.get());
        baiViet.setBinhLuanBaiViets(null);
        baiViet.setNguoiDungThichBaiViets(null);
        baiVietRepo.save(baiViet);
        baiViet.setBinhLuanBaiViets(themListBinhLuanBaiViet(baiViet.getId(), request.getBinhLuanBaiViets()));
        baiViet.setNguoiDungThichBaiViets(themListNguoiDungThichBaiViet(baiViet.getId(), request.getNguoiDungThichBaiViets()));
        return _responseObject.responseSuccess("Them bai viet thanh cong", _converter.entityToDTO(baiViet));
    }

    @Override
    public BaiViet suaBaiViet(BaiViet baiVietSua) {
        Optional<BaiViet> baiVietOptional = baiVietRepo.findById(baiVietSua.getId());
        if (baiVietOptional.isEmpty()) {
            return null;
        }
        BaiViet baiViet = baiVietOptional.get();
        baiViet.setTieuDe(baiVietSua.getTieuDe());
        baiViet.setMoTa(baiVietSua.getMoTa());
        baiViet.setNoiDung(baiVietSua.getNoiDung());
        baiViet.setThoiGianCapNhat(LocalDate.now());
        baiViet.setTrangThaiBaiViet(baiVietSua.getTrangThaiBaiViet());
        baiVietRepo.save(baiViet);
        return baiViet;
    }

    @Override
    public String xoaBaiViet(int baiVietId) {
        Optional<BaiViet> baiVietOptional = baiVietRepo.findById(baiVietId);
        if (baiVietOptional.isEmpty()) {
            return null;
        }
        binhLuanBaiVietRepo.findAll().forEach(x -> {
            BaiViet baiViet = x.getBaiViet();
            if (baiViet != null && baiViet.getId() == baiVietId) {
                binhLuanBaiVietRepo.delete(x);
            }
        });
        nguoiDungThichBaiVietRepo.findAll().forEach(x-> {
            BaiViet baiViet = x.getBaiViet();
            if (baiViet != null && baiViet.getId() == baiVietId) {
                nguoiDungThichBaiVietRepo.delete(x);
            }
        });
        BaiViet baiViet = baiVietOptional.get();
        baiVietRepo.delete(baiViet);
        baiViet.setThoiGianXoa(LocalDate.now());
        baiViet.setDaXoa(true);
        return "Xoa thanh cong";
    }

    @Override
    public BaiViet getBaiViet(int baiVietId) {
        Optional<BaiViet> baiVietOptional = baiVietRepo.findById(baiVietId);
        if (baiVietOptional.isEmpty()) {
            return null;
        }
        BaiViet baiViet = baiVietOptional.get();
        return baiViet;
    }
}
