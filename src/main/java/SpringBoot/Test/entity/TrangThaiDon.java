package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "trangthaidon")
@Getter
@Setter
public class TrangThaiDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tentrangthai")
    private String tenTrangThai;

    @OneToMany(mappedBy = "trangThaiDon")
    @JsonManagedReference("trangthaidon-dondangky")
    private Set<DonDangKy> donDangKys;
}
