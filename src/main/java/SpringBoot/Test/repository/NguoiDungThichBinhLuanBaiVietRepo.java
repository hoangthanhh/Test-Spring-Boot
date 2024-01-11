package SpringBoot.Test.repository;

import SpringBoot.Test.entity.NguoiDungThichBinhLuanBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungThichBinhLuanBaiVietRepo extends JpaRepository<NguoiDungThichBinhLuanBaiViet,Integer> {
//    @Query(value = "select count(n.id) from nguoidungthichbinhluanbaiviet n join binhluanbaiviet b on b.id = n.binhluanbaivietid where b.id = :Id")
//    int soNguoiDungThichBLBV(@Param("Id") int Id);
}
