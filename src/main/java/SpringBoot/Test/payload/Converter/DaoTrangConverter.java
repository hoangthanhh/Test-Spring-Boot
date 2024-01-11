package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.DaoTrang;
import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTuDaoTrang;
import SpringBoot.Test.entity.QuyenHan;
import SpringBoot.Test.payload.DataResponse.DaoTrangDTO;
import SpringBoot.Test.payload.DataResponse.QuyenHanDTO;
import SpringBoot.Test.repository.DaoTrangRepo;
import SpringBoot.Test.repository.DonDangKyRepo;
import SpringBoot.Test.repository.PhatTuDaoTrangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DaoTrangConverter {
    @Autowired
    private DaoTrangRepo _daoTrangRepo;
    @Autowired
    private PhatTuDaoTrangRepo _phatTuDaoTrangRepo;
    @Autowired
    private DonDangKyRepo _donDangKyRepo;
    private final PhatTuDaoTrangConverter _converter;
    private final DonDangKyConverter _donConverter;
    public DaoTrangConverter(PhatTuDaoTrangConverter converter ,DonDangKyConverter donConverter) {
        _converter = converter;
        _donConverter = donConverter;
    }
    public DaoTrangDTO entityToDTO(DaoTrang daoTrang) {
        DaoTrangDTO daoTrangDTO = new DaoTrangDTO();
        daoTrangDTO.setDaKetThuc(daoTrang.getDaKetThuc());
        daoTrangDTO.setNoiDung(daoTrang.getNoiDung());
        daoTrangDTO.setNoiToChuc(daoTrang.getNoiToChuc());
        daoTrangDTO.setSoThanhVienThamGia(daoTrang.getSoThanhVienThamGia());
        daoTrangDTO.setThoiGianBatDau(daoTrang.getThoiGianBatDau());
        daoTrangDTO.setNguoiTruTri(daoTrang.getNguoiTruTri());
        daoTrangDTO.setDonDangKys(_donDangKyRepo.findAll().stream().filter(x -> x.getDaoTrang().getId() == daoTrang.getId()).map(_donConverter::entityToDTO).collect(Collectors.toSet()));
        daoTrangDTO.setPhatTuDaoTrangs(_phatTuDaoTrangRepo.findAll().stream().filter(x -> x.getDaoTrang().getId() == daoTrang.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return daoTrangDTO;
    }
}
