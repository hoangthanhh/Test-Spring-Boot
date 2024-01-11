package SpringBoot.Test.service;

import SpringBoot.Test.entity.DonDangKy;
import SpringBoot.Test.entity.PhatTu;
import SpringBoot.Test.payload.DataResponse.DonDangKyDTO;
import SpringBoot.Test.payload.DataResponse.PhatTuDTO;

import java.util.List;

public interface IDonDangKy {
    public String themListDonDangKy(List<DonDangKy> list, int trangThaiDonId);
    public DonDangKy suaDonDangKy(DonDangKy donDangKySua);
}
