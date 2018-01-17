package ml.ledv.spring.boot.apache.tiles.db.repository.jpa;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, String> {

    List<BookEntity> findByName(String name);
}
