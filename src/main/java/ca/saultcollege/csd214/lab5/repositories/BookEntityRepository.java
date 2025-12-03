package ca.saultcollege.csd214.lab5.repositories;

import ca.saultcollege.csd214.lab5.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    // custom derived query (required by lab)
    List<BookEntity> findByAuthor(String author);
}
