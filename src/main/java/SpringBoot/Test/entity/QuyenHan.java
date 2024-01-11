package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "quyenhan")
@Getter
@Setter
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tenquyenhan")
    private String tenQuyenHan;

    @OneToMany(mappedBy = "quyenHan")
    @JsonManagedReference("quyenhan-phattu")
    private Set<PhatTu> phatTus;
}
