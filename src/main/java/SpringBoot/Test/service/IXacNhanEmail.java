package SpringBoot.Test.service;

import SpringBoot.Test.entity.TrangThaiDon;
import SpringBoot.Test.entity.XacNhanEmail;

import java.util.List;

public interface IXacNhanEmail {
    public String themListXacNhanEmail(List<XacNhanEmail> list, int phatTuId);
    public XacNhanEmail suaXacNhanEmail(XacNhanEmail xacNhanEmailSua);
}
