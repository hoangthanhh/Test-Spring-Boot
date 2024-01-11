package SpringBoot.Test.repository;

import SpringBoot.Test.entity.DaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoTrangRepo extends JpaRepository<DaoTrang,Integer> {
}
