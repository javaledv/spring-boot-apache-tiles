package ml.ledv.spring.boot.apache.tiles.db.repository.jpa;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.ContentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContentRepository extends PagingAndSortingRepository<ContentEntity, String> {
}
