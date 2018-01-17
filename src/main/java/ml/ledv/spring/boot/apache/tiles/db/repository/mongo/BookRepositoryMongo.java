package ml.ledv.spring.boot.apache.tiles.db.repository.mongo;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepositoryMongo extends MongoRepository<BookEntity, String> {

    List<BookEntity> findByName(String name);
}
