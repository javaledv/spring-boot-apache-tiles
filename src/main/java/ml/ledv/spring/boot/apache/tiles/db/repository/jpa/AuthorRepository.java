package ml.ledv.spring.boot.apache.tiles.db.repository.jpa;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.AuthorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, String> {
}
