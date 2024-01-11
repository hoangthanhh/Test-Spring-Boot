package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "chua")
@Getter
@Setter
public class Chua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tenchua")
    private String tenChua;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "ngaythanhlap")
    private LocalDate ngayThanhLap;

    @Column(name = "nguoitrutri")
    private String nguoiTruTri;

    @OneToMany(mappedBy = "chua")
    @JsonManagedReference("chua-phattu")
    private Set<PhatTu> phatTus;
}
