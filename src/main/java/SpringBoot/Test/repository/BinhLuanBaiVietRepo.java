package SpringBoot.Test.repository;

import SpringBoot.Test.entity.BinhLuanBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinhLuanBaiVietRepo extends JpaRepository<BinhLuanBaiViet,Integer> {
}
