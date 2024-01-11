package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.QuyenHan;
import SpringBoot.Test.payload.DataResponse.QuyenHanDTO;
import SpringBoot.Test.repository.PhatTuRepo;
import SpringBoot.Test.repository.QuyenHanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuyenHanConverter {
    @Autowired
    private QuyenHanRepo _quyenHanRepo;
    @Autowired
    private PhatTuRepo _phatTuRepo;
    private final PhatTuConverter _converter;
    public QuyenHanConverter(PhatTuConverter converter) {
        _converter = converter;
    }
    public QuyenHanDTO entityToDTO(QuyenHan quyenHan) {
        QuyenHanDTO quyenHanDTO = new QuyenHanDTO();
        quyenHanDTO.setTenQuyenHan(quyenHan.getTenQuyenHan());
        quyenHanDTO.setPhatTus(_phatTuRepo.findAll().stream().filter(x -> x.getQuyenHan().getId() == quyenHan.getId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return quyenHanDTO;
    }
}
