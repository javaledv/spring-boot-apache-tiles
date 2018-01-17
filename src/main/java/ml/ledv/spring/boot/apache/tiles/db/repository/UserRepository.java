package ml.ledv.spring.boot.apache.tiles.db.repository;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {

    Optional<UserEntity> findByBooks(BookEntity bookEntity);

    @Transactional
    @Modifying
    @Query("update USER u set u.id = :newId where u.id = :id")
    void updateId(@Param("newId") String newId, @Param("id") String id);

    List<UserEntity> findByLogin(String login);
}
