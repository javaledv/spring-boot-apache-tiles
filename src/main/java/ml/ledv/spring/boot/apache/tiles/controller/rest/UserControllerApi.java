package ml.ledv.spring.boot.apache.tiles.controller.rest;

import ml.ledv.spring.boot.apache.tiles.controller.rest.params.BookParam;
import ml.ledv.spring.boot.apache.tiles.controller.rest.params.UserParams;
import ml.ledv.spring.boot.apache.tiles.controller.rest.responce.ErrorResponse;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import ml.ledv.spring.boot.apache.tiles.db.service.BookService;
import ml.ledv.spring.boot.apache.tiles.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/users")
public class UserControllerApi {

    private UserService userService;
    private BookService bookService;

    @Autowired
    public UserControllerApi(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping(produces = {"application/json", "application/json"})
    public ResponseEntity<?> createUser(@RequestBody final UserParams userParams) {

        final String login = userParams.getLogin();

        if (login == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Empty field login"));
        } else {
            userService.createUser(login);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(produces = {"application/xml", "application/json"})
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping(value = "/{id}", produces = {"application/xml", "application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable final String id) {

        final Optional<UserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            userService.deleteUser(userOptional.get());
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping(value = "/{id}", produces = {"application/xml", "application/json"})
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
}
