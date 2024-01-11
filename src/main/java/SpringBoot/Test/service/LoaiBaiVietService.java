package SpringBoot.Test.service;

import SpringBoot.Test.entity.BaiViet;
import SpringBoot.Test.entity.LoaiBaiViet;
import SpringBoot.Test.payload.Converter.LoaiBaiVietConverter;
import SpringBoot.Test.payload.DataRequest.ThemBaiVietRequest;
import SpringBoot.Test.payload.DataRequest.ThemLoaiBaiVietRequest;
import SpringBoot.Test.payload.DataResponse.LoaiBaiVietDTO;
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
public class LoaiBaiVietService implements ILoaiBaiViet{
    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private TrangThaiBaiVietRepo trangThaiBaiVietRepo;

    private final LoaiBaiVietConverter _converter;
    private final ResponseObject<LoaiBaiVietDTO> _responseObject;
    public LoaiBaiVietService(LoaiBaiVietConverter converter, ResponseObject<LoaiBaiVietDTO> responseObject) {
        _converter = converter;
        _responseObject = responseObject;
    }

    private Set<BaiViet> themListBaiViet(int loaiBaiVietId, List<ThemBaiVietRequest> requests) {
        var loaiBaiViet = loaiBaiVietRepo.findById(loaiBaiVietId);
        if (loaiBaiViet.isEmpty()) {
            return null;
        }
        Set<BaiViet> set = new HashSet<>();
        for (var request: requests) {
            BaiViet baiViet = new BaiViet();
            baiViet.setLoaiBaiViet(loaiBaiViet.get());
            baiViet.setTieuDe(request.getTieuDe());
            baiViet.setMoTa(request.getMoTa());
            baiViet.setNoiDung(request.getNoiDung());
            baiViet.setNguoiDuyetBaiVietId(request.getNguoiDuyetBaiVietId());
            baiViet.setSoLuotThich(request.getSoLuotThich());
            baiViet.setSoLuotBinhLuan(request.getSoLuotBinhLuan());
            baiViet.setThoiGianDang(request.getThoiGianDang());
//            baiViet.setThoiGianCapNhat(request.getThoiGianCapNhat());
//            baiViet.setThoiGianXoa(request.getThoiGianXoa());
//            baiViet.setDaXoa(request.getDaXoa());
            var phatTu = phatTuRepo.findById(request.getPhatTuId()).get();
            baiViet.setPhatTu(phatTu);
            var trangThaiBaiViet = trangThaiBaiVietRepo.findById(request.getTrangThaiBaiVietId()).get();
            baiViet.setTrangThaiBaiViet(trangThaiBaiViet);
            set.add(baiViet);
        }
        baiVietRepo.saveAll(set);
        loaiBaiViet.get().setBaiViets(set);
        loaiBaiVietRepo.save(loaiBaiViet.get());
        return set;
    }

    @Override
    public ResponseObject<LoaiBaiVietDTO> themLoaiBaiVietRequest(ThemLoaiBaiVietRequest request) {
        LoaiBaiViet loaiBaiViet = new LoaiBaiViet();
        loaiBaiViet.setTenLoai(request.getTenLoai());
        loaiBaiViet.setBaiViets(null);
        loaiBaiVietRepo.save(loaiBaiViet);
        loaiBaiViet.setBaiViets(themListBaiViet(loaiBaiViet.getId(), request.getBaiViets()));
        return _responseObject.responseSuccess("Them loai bai viet thanh cong", _converter.entityToDTO(loaiBaiViet));
    }

    @Override
    public LoaiBaiViet suaLoaiBaiViet(LoaiBaiViet loaiBaiVietSua) {
        Optional<LoaiBaiViet> loaiBaiVietOptional = loaiBaiVietRepo.findById(loaiBaiVietSua.getId());
        if (loaiBaiVietOptional.isEmpty()) {
            return null;
        }
        LoaiBaiViet loaiBaiViet = loaiBaiVietOptional.get();
        loaiBaiViet.setTenLoai(loaiBaiVietSua.getTenLoai());
        loaiBaiViet.setBaiViets(loaiBaiVietSua.getBaiViets());
        loaiBaiVietRepo.save(loaiBaiViet);
        return loaiBaiViet;
    }
}
