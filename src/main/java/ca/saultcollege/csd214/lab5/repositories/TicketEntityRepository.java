package ca.saultcollege.csd214.lab5.repositories;

import ca.saultcollege.csd214.lab5.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {
}
