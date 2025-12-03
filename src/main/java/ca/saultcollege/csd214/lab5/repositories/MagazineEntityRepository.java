package ca.saultcollege.csd214.lab5.repositories;

import ca.saultcollege.csd214.lab5.entities.MagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineEntityRepository extends JpaRepository<MagazineEntity, Long> {
}
