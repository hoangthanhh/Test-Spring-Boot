package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Table(name = "dondangky")
@Getter
@Setter
public class DonDangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "ngayguidon")
    private LocalDate ngayGuiDon;

    @Column(name = "ngayxuly")
    private LocalDate ngayXuLy;

    @Column(name = "nguoixuly")
    private Integer nguoiXuLy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("trangthaidon-dondangky")
    @JoinColumn(name = "trangThaiDonId")
    private TrangThaiDon trangThaiDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("daotrang-dondangky")
    @JoinColumn(name = "daoTrangId")
    private DaoTrang daoTrang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-dondangky")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;
}
