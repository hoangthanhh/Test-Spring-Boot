package SpringBoot.Test.service;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.TrangThaiBaiViet;
import SpringBoot.Test.payload.Converter.TrangThaiBaiVietConverter;
import SpringBoot.Test.payload.DataRequest.ThemBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemTrangThaiBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.TrangThaiBaiVietDTO;
import SpringBoot.Test.payload.Response.ResponseObject;
import SpringBoot.Test.repository.BaiVietRepo;
import SpringBoot.Test.repository.LoaiBaiVietRepo;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.TrangThaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TrangThaiBaiVietService implements ITrangThaiBaiViet {
    @Autowired
    private TrangThaiBaiVietRepo trangThaiBaiVietRepo;
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;

    private final TrangThaiBaiVietConverter _converter;
    private final ResponseObject<TrangThaiBaiVietDTO> _responseObject;
    public TrangThaiBaiVietService(TrangThaiBaiVietConverter converter, ResponseObject<TrangThaiBaiVietDTO> responseObject) {
        _converter = converter;
        _responseObject = responseObject;
    }

    private Set<BaiViet> themListBaiViet(int trangThaiBaiVietId, List<ThemBaiVietRequest> requests) {
        var trangThaiBaiViet = trangThaiBaiVietRepo.findById(trangThaiBaiVietId);
        if (trangThaiBaiViet.isEmpty()) {
            return null;
        }
        Set<BaiViet> set = new HashSet<>();
        for (var request: requests) {
            BaiViet baiViet = new BaiViet();
            baiViet.setTrangThaiBaiViet(trangThaiBaiViet.get());
            baiViet.setTieuDe(request.getTieuDe());
            baiViet.setMoTa(request.getMoTa());
            baiViet.setNoiDung(request.getNoiDung());
            baiViet.setNguoiDuyetBaiVietId(request.getNguoiDuyetBaiVietId());
            baiViet.setSoLuotThich(request.getSoLuotThich());
            baiViet.setSoLuotBinhLuan(request.getSoLuotBinhLuan());
            baiViet.setThoiGianDang(request.getThoiGianDang());
            baiViet.setThoiGianCapNhat(request.getThoiGianCapNhat());
            baiViet.setThoiGianXoa(request.getThoiGianXoa());
            baiViet.setDaXoa(request.getDaXoa());
            var loaiBaiViet = loaiBaiVietRepo.findById(request.getLoaiBaiVietId()).get();
            baiViet.setLoaiBaiViet(loaiBaiViet);
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            baiViet.setPhatTu(phatTu);
            set.add(baiViet);
        }
        baiVietRepo.saveAll(set);
        trangThaiBaiViet.get().setBaiViets(set);
        trangThaiBaiVietRepo.save(trangThaiBaiViet.get());
        return set;
    }
    @Override
    public ResponseObject<TrangThaiBaiVietDTO> themTrangThaiBaiVietRequest(ThemTrangThaiBaiVietRequest request) {
        TrangThaiBaiViet trangThaiBaiViet = new TrangThaiBaiViet();
        trangThaiBaiViet.setTenTrangThai(request.getTenTrangThai());
        trangThaiBaiViet.setBaiViets(null);
        trangThaiBaiVietRepo.save(trangThaiBaiViet);
        trangThaiBaiViet.setBaiViets(themListBaiViet(trangThaiBaiViet.getId(),request.getBaiViets()));
        return _responseObject.responseSuccess("Them trang thai bai viet thanh cong", _converter.entityToDTO(trangThaiBaiViet));
    }

    @Override
    public TrangThaiBaiViet suaTrangThaiBaiViet(TrangThaiBaiViet trangThaiBaiVietSua) {
        Optional<TrangThaiBaiViet> trangThaiBaiVietOptional = trangThaiBaiVietRepo.findById(trangThaiBaiVietSua.getId());
        if (trangThaiBaiVietOptional.isEmpty()) {
            return null;
        }
        TrangThaiBaiViet trangThaiBaiViet = trangThaiBaiVietOptional.get();
        trangThaiBaiViet.setTenTrangThai(trangThaiBaiVietSua.getTenTrangThai());
        trangThaiBaiViet.setBaiViets(trangThaiBaiVietSua.getBaiViets());
        trangThaiBaiVietRepo.save(trangThaiBaiViet);
        return trangThaiBaiViet;
    }
}
