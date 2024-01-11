package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "daotrang")
@Getter
@Setter
public class DaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "daketthuc")
    private Boolean daKetThuc;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "noitochuc")
    private String noiToChuc;

    @Column(name = "sothanhvienthamgia")
    private Integer soThanhVienThamGia;

    @Column(name = "thoigianbatdau")
    private LocalDate thoiGianBatDau;

    @Column(name = "nguoitrutri")
    private Integer nguoiTruTri;

    @OneToMany(mappedBy = "daoTrang")
    @JsonManagedReference("daotrang-dondangky")
    private Set<DonDangKy> donDangKys;

    @OneToMany(mappedBy = "daoTrang")
    @JsonManagedReference("daotrang-phattudaotrang")
    private Set<PhatTuDaoTrang> phatTuDaoTrangs;
}
