package SpringBoot.Test.repository;

import SpringBoot.Test.entity.TrangThaiBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiBaiVietRepo extends JpaRepository<TrangThaiBaiViet,Integer> {
}
