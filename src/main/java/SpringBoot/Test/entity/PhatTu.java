package SpringBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "phattu")
@Getter
@Setter
public class PhatTu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tentaikhoan")
    private String tenTaiKhoan;

    @Column(name = "anhchup")
    private String anhChup;

    @Column(name = "dahoantuc")
    private Boolean daHoanTuc;

    @Column(name = "email")
    private String email;

    @Column(name = "gioitinh")
    private String gioiTinh;

    @Column(name = "ngaycapnhat")
    private LocalDate ngayCapNhat;

    @Column(name = "ngayhoantuc")
    private LocalDate ngayHoanTuc;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "phapdanh")
    private String phapDanh;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-dondangky")
    private Set<DonDangKy> donDangKyS;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-phattudaotrang")
    private Set<PhatTuDaoTrang> phatTuDaoTrangs;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-refreshtoken")
    private Set<RefreshToken> refreshTokens;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-email")
    private Set<XacNhanEmail> xacNhanEmails;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-baiviet")
    private Set<BaiViet> baiViets;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-nguoidungthichbaiviet")
    private Set<NguoiDungThichBaiViet> nguoiDungThichBaiViets;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-binhluanbaiviet")
    private Set<BinhLuanBaiViet> binhLuanBaiViets;

    @OneToMany(mappedBy = "phatTu")
    @JsonManagedReference("phattu-nguoidungthichbinhluanbaiviet")
    private Set<NguoiDungThichBinhLuanBaiViet> nguoiDungThichBinhLuanBaiViets;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("chua-phattu")
    @JoinColumn(name = "chuaId")
    private Chua chua;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("quyenhan-phattu")
    @JoinColumn(name = "quyenHanId")
    private QuyenHan quyenHan;

}
