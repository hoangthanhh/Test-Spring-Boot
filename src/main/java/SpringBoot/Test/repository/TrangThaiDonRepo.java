package SpringBoot.Test.repository;

import SpringBoot.Test.entity.TrangThaiDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiDonRepo extends JpaRepository<TrangThaiDon,Integer> {
}
