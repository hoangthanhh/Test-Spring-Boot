package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "nguoidungthichbaiviet")
@Getter
@Setter
public class NguoiDungThichBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "thoigianthich")
    private LocalDate thoiGianThich;

    @Column(name = "daxoa")
    private Boolean daXoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-nguoidungthichbaiviet")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("baiviet-nguoidungthichbaiviet")
    @JoinColumn(name = "baiVietId")
    private BaiViet baiViet;
}
