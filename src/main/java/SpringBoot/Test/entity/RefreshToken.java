package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "refreshtoken")
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "token")
    private String token;

    @Column(name = "thoigianhethan")
    private LocalDate thoiGianHetHan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-refreshtoken")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;
}
