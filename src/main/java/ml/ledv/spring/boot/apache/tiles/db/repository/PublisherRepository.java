package ml.ledv.spring.boot.apache.tiles.db.repository;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.PublisherEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PublisherRepository extends PagingAndSortingRepository<PublisherEntity, String> {
}
