package SpringBoot.Test.repository;

import SpringBoot.Test.entity.PhatTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhatTuRepo extends JpaRepository<PhatTu,Integer> {
}
