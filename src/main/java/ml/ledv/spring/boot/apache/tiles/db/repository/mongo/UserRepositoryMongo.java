package ml.ledv.spring.boot.apache.tiles.db.repository.mongo;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByBooks(BookEntity bookEntity);

    List<UserEntity> findByLogin(String login);
}
