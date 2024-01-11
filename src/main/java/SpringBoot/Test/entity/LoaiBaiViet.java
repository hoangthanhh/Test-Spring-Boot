package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "loaibaiviet")
@Getter
@Setter
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tenloai")
    private String tenLoai;

    @OneToMany(mappedBy = "loaiBaiViet")
    @JsonManagedReference("loaibaiviet-baiviet")
    private Set<BaiViet> baiViets;
}
