package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "xacnhanemail")
@Getter
@Setter
public class XacNhanEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "thoigianhethan")
    private LocalDate thoiGianHetHan;

    @Column(name = "maxacnhan")
    private String maXacNhan;

    @Column(name = "daxacnhan")
    private Boolean daXacNhan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-email")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;
}
