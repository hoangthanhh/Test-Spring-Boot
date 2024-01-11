package SpringBoot.Test.repository;

import SpringBoot.Test.entity.DonDangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonDangKyRepo extends JpaRepository<DonDangKy,Integer> {
}
