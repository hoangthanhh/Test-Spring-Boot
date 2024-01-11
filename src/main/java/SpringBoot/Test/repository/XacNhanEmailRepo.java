package SpringBoot.Test.repository;

import SpringBoot.Test.entity.XacNhanEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XacNhanEmailRepo extends JpaRepository<XacNhanEmail,Integer> {
}
