package SpringBoot.Test.repository;

import SpringBoot.Test.entity.QuyenHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuyenHanRepo extends JpaRepository<QuyenHan,Integer> {
}
