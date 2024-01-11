package SpringBoot.Test.payload.Converter;

import SpringBoot.Test.entity.XacNhanEmail;
import SpringBoot.Test.payload.DataResponse.XacNhanEmailDTO;
import org.springframework.stereotype.Component;

@Component
public class XacNhanEmailConverter {
    public XacNhanEmailDTO entityToDTO(XacNhanEmail xacNhanEmail) {
        XacNhanEmailDTO xacNhan =new XacNhanEmailDTO();
        xacNhan.setId(xacNhanEmail.getId());
        xacNhan.setMaXacNhan(xacNhanEmail.getMaXacNhan());
        xacNhan.setDaXacNhan(xacNhanEmail.getDaXacNhan());
        xacNhan.setThoiGianHetHan(xacNhanEmail.getThoiGianHetHan());
        return xacNhan;
    }
}
