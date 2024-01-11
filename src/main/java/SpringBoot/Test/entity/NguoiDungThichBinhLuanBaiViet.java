package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "nguoidungthichbinhluanbaiviet")
@Getter
@Setter
public class NguoiDungThichBinhLuanBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "thoigianlike")
    private LocalDate thoiGianLike;

    @Column(name = "daxoa")
    private Boolean daXoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-nguoidungthichbinhluanbaiviet")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("binhluanbaiviet-nguoidungthichbinhluanbaiviet")
    @JoinColumn(name = "binhLuanBaiVietId")
    private BinhLuanBaiViet binhLuanBaiViet;
}
