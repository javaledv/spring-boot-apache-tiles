package ml.ledv.spring.boot.apache.tiles.db.repository;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.NewspaperEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewspaperRepository extends PagingAndSortingRepository<NewspaperEntity, String> {
}
