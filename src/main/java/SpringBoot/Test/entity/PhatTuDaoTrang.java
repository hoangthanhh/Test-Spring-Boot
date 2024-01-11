package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "phattudaotrang")
@Getter
@Setter
public class PhatTuDaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "dathamgia")
    private Boolean daThamGia;

    @Column(name = "lydokhongthamgia")
    private String lyDoKhongThamGia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("daotrang-phattudaotrang")
    @JoinColumn(name = "daoTrangId")
    private DaoTrang daoTrang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-phattudaotrang")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;
}
