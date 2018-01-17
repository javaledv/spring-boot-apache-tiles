package ml.ledv.spring.boot.apache.tiles.db.repository.jpa;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {

    Optional<UserEntity> findByBooks(BookEntity bookEntity);

    List<UserEntity> findByLogin(String login);
}
