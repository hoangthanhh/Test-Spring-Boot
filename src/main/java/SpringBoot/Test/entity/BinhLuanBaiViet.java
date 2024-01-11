package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "binhluanbaiviet")
@Getter
@Setter
public class BinhLuanBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "binhluan")
    private String binhLuan;

    @Column(name = "soluotthich")
    private Integer soLuotThich;

    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;

    @Column(name = "thoigiancapnhat")
    private LocalDate thoiGianCapNhat;

    @Column(name = "thoigianxoa")
    private LocalDate thoiGianXoa;

    @Column(name = "daxoa")
    private Boolean daXoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("baiviet-binhluanbaiviet")
    @JoinColumn(name = "baiVietId")
    private BaiViet baiViet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-binhluanbaiviet")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;

    @OneToMany(mappedBy = "binhLuanBaiViet", cascade = CascadeType.ALL)
    @JsonManagedReference("binhluanbaiviet-nguoidungthichbinhluanbaiviet")
    private Set<NguoiDungThichBinhLuanBaiViet> nguoiDungThichBinhLuanBaiViets;
}
