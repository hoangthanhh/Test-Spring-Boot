package SpringBoot.Test.service;

import SpringBoot.Test.entity.PhatTuDaoTrang;

import java.util.List;

public interface IPhatTuDaoTrang {
    public String themListPhatTuDaoTrang(List<PhatTuDaoTrang> list, int phatTuId, int daoTrangId);
    public PhatTuDaoTrang suaPhatTuDaoTrang(PhatTuDaoTrang phatTuDaoTrangSua);
}
