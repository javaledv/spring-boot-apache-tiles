package ml.ledv.spring.boot.apache.tiles.controller.rest;

import ml.ledv.spring.boot.apache.tiles.controller.rest.params.BookParam;
import ml.ledv.spring.boot.apache.tiles.controller.rest.params.UserParams;
import ml.ledv.spring.boot.apache.tiles.controller.rest.responce.ErrorResponse;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import ml.ledv.spring.boot.apache.tiles.db.service.BookService;
import ml.ledv.spring.boot.apache.tiles.db.service.UserService;
import ml.ledv.spring.boot.apache.tiles.utils.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(Routing.API_PATH)
public class BookLibraryRestAPI {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public BookLibraryRestAPI(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/users", produces={"application/json", "application/json"})
    public ResponseEntity<?> createUser(@RequestBody final UserParams userParams) {

        final String login = userParams.getLogin();

        if (login == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Empty field login"));
        } else {
            userService.createUser(login);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/users", produces={"application/xml", "application/json"})
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping(value = "/users/{id}", produces={"application/xml", "application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable final String id) {

        final Optional<UserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            userService.deleteUser(userOptional.get());
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping(value = "/users/{id}", produces={"application/xml", "application/json"})
    public ResponseEntity<?> reserveBook(@PathVariable final String id, @RequestBody final BookParam bookParams) {

        final Optional<UserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Not found user with id. " + id));
        } else {

            final String bookId = bookParams.getId();

            if (bookId == null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Empty id field. "));
            } else {

                final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

                if (!bookOptional.isPresent()) {
                    return ResponseEntity.badRequest().body(new ErrorResponse("Not found book with id. " + bookId));
                } else {

                    final UserEntity userEntity = userOptional.get();
                    final BookEntity bookEntity = bookOptional.get();

                    userEntity.getBooks().add(bookEntity);

                    userService.updateUser(userEntity);

                    return ResponseEntity.ok().build();
                }
            }
        }
    }

    @PostMapping(value = "/books", produces={"application/xml", "application/json"})
    public ResponseEntity<?> createBook(@RequestBody final BookParam bookParams) {

        final String name = bookParams.getName();

        if (name == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Empty field name"));
        } else {
            bookService.createBook(name);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @GetMapping(value = "/books", produces={"application/xml", "application/json"})
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(value = "/books/free", produces={"application/xml", "application/json"})
    public ResponseEntity<?> getBooksFree(){

        final List<BookEntity> books = bookService.getAll();
        final List<BookEntity> freeBooks = new ArrayList<>();


        for(BookEntity book: books){
            if(!userService.getUserByBook(book).isPresent()){
                freeBooks.add(book);
            }
        }

        return ResponseEntity.ok(freeBooks);
    }

    @DeleteMapping(value = "/books/{id}", produces={"application/xml", "application/json"})
    public ResponseEntity<?> deleteBook(@PathVariable final String id) {

        final Optional<BookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            bookService.deleteBook(bookOptional.get());
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping(value = "/books/{id}", produces={"application/xml", "application/json"})
    public ResponseEntity<?> cancelBookReservation(@PathVariable final String id, @RequestBody final UserParams userParams) {

        final String userId = userParams.getId();
        if (userId == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Empty field id. "));
        } else {
            final Optional<BookEntity> bookOptional = bookService.getBookById(id);

            if (!bookOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Not found book with id. " + id));
            } else {

                final Optional<UserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    return ResponseEntity.badRequest().body(new ErrorResponse("Not found user with id. " + userId));
                } else {
                    userService.removeBook(userOptional.get(), bookOptional.get());
                    return ResponseEntity.ok().build();
                }
            }
        }
    }
}