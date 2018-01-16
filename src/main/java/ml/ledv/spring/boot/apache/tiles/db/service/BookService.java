package ml.ledv.spring.boot.apache.tiles.db.service;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookEntity createBook(String name);

    void deleteBook(BookEntity book);

    List<BookEntity> getAll();

    Optional<BookEntity> getBookById(String id);

    BookEntity saveBook(BookEntity book);

    void updateId(String newId, String id);

    List<BookEntity> getAllByName(String name);
}
