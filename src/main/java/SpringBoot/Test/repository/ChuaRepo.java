package SpringBoot.Test.repository;

import SpringBoot.Test.entity.Chua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuaRepo extends JpaRepository<Chua,Integer> {
}
