package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "baiviet")
@Getter
@Setter
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "nguoiduyetbaivietid")
    private Integer nguoiDuyetBaiVietId;

    @Column(name = "soluotthich")
    private Integer soLuotThich;

    @Column(name = "soluotbinhluan")
    private Integer soLuotBinhLuan;

    @Column(name = "thoigiandang")
    private LocalDate thoiGianDang;

    @Column(name = "thoigiancapnhat")
    private LocalDate thoiGianCapNhat;

    @Column(name = "thoigianxoa")
    private LocalDate thoiGianXoa;

    @Column(name = "daxoa")
    private Boolean daXoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("loaibaiviet-baiviet")
    @JoinColumn(name = "loaiBaiVietId")
    private LoaiBaiViet loaiBaiViet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("phattu-baiviet")
    @JoinColumn(name = "phatTuId")
    private PhatTu phatTu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("trangthaibaiviet-baiviet")
    @JoinColumn(name = "trangThaiBaiVietId")
    private TrangThaiBaiViet trangThaiBaiViet;

    @OneToMany(mappedBy = "baiViet")
    @JsonManagedReference("baiviet-binhluanbaiviet")
    private Set<BinhLuanBaiViet> binhLuanBaiViets;

    @OneToMany(mappedBy = "baiViet")
    @JsonManagedReference("baiviet-nguoidungthichbaiviet")
    private Set<NguoiDungThichBaiViet> nguoiDungThichBaiViets;
}
