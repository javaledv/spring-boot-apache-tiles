package ml.ledv.spring.boot.apache.tiles.controller.mvc;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import ml.ledv.spring.boot.apache.tiles.db.service.BookService;
import ml.ledv.spring.boot.apache.tiles.db.service.UserService;
import ml.ledv.spring.boot.apache.tiles.utils.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(Routing.BOOKS_PATH)
public class BookController {

    private static final boolean FREE_BOOK = true;

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(final BookService bookService, final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getBooks() {

        final List<BookEntity> books = bookService.getAll();
        final ModelAndView modelAndView = new ModelAndView("showBooks");

        final Map<BookEntity, Boolean> resultBooksMap = new HashMap<>();

        for (BookEntity bookEntity : books) {
            if (userService.getUserByBook(bookEntity).isPresent()) {
                resultBooksMap.put(bookEntity, !FREE_BOOK);
            } else {
                resultBooksMap.put(bookEntity, FREE_BOOK);
            }
        }

        modelAndView.addObject("books", resultBooksMap);

        return modelAndView;
    }

    @GetMapping("/free")
    public ModelAndView getFreeBooks() {

        final ModelAndView modelAndView = new ModelAndView("showFreeBooks");
        final List<BookEntity> books = bookService.getAll();
        final Map<BookEntity, Boolean> freeBooks = new HashMap<>();

        for (BookEntity bookEntity : books) {
            Optional<UserEntity> userOptional = userService.getUserByBook(bookEntity);

            if (!userOptional.isPresent()) {
                freeBooks.put(bookEntity, FREE_BOOK);
            }
        }
        modelAndView.addObject("books", freeBooks);

        return modelAndView;
    }

    @PostMapping("/users")
    public ModelAndView showUsers(@RequestParam("id") final String id) {

        final ModelAndView modelAndView = new ModelAndView("chooseUser");
        final List<UserEntity> users = userService.getAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("bookId", id);

        return modelAndView;
    }

    @PostMapping("/reservation")
    public ModelAndView reserveBook(@RequestParam("bookId") final String bookId, @RequestParam("userId") final String userId) {

        final Optional<UserEntity> userOptional = userService.getUserById(userId);
        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        userService.addBook(userOptional.get(), bookOptional.get());

        return new ModelAndView("redirect:/books");
    }

    @PostMapping("/reservation/cancel")
    public ModelAndView cancelReservation(@RequestParam("bookId") final String bookId) {

        final ModelAndView modelAndViewError = new ModelAndView("error");

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            modelAndViewError.addObject("errMsg", "Book with id: " + bookId + " is not exist!");
            return modelAndViewError;
        } else {

            final BookEntity bookEntity = bookOptional.get();
            final Optional<UserEntity> userOptional = userService.getUserByBook(bookEntity);

            if (!userOptional.isPresent()) {
                modelAndViewError.addObject("errMsg", "Book with id: " + bookId + " is not reserved!");
                return modelAndViewError;
            } else {

                userService.removeBook(userOptional.get(), bookEntity);

                return new ModelAndView("redirect:/books");
            }
        }
    }

    @PostMapping("/add")
    public ModelAndView addBook(@ModelAttribute("bookEntity") final BookEntity bookEntity) {

        final String bookName = bookEntity.getName();
        if (bookName.length() < 1) {
            final ModelAndView modelAndView = new ModelAndView("redirect:/books/add/form");
            modelAndView.addObject("error", "Empty field name!");
            return modelAndView;
        }

        bookService.createBook(bookEntity.getName());

        return new ModelAndView("redirect:/books");
    }

    @GetMapping("/add/form")
    public ModelAndView addBookForm(@RequestParam(value = "error", required = false) String error) {

        ModelAndView modelAndView = new ModelAndView("showBooksAddForm");
        modelAndView.addObject("bookEntity", new BookEntity());
        modelAndView.addObject("error", error);

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView findBooks(@RequestParam(value = "bookName") final String bookName) {
        final List<BookEntity> books = bookService.getAllByName(bookName);

        final Map<BookEntity, Boolean> result = new HashMap<>();

        for (BookEntity bookEntity : books) {
            if (userService.getUserByBook(bookEntity).isPresent()) {
                result.put(bookEntity, !FREE_BOOK);
            } else {
                result.put(bookEntity, FREE_BOOK);
            }
        }

        final ModelAndView modelAndView = new ModelAndView("showBooks");
        modelAndView.addObject("books", result);

        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView removeBook(@RequestParam(value = "bookId") final String bookId) {

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            final ModelAndView errorModelEndView = new ModelAndView("error");
            errorModelEndView.addObject("error", "Book with id " + bookId + " is not exist!");
            return errorModelEndView;
        } else {
            final ModelAndView modelAndView = new ModelAndView("redirect:/books");
            bookService.deleteBook(bookOptional.get());

            return modelAndView;
        }
    }

    @GetMapping("/users/search")
    public ModelAndView searchUser(@ModelAttribute("users") final ArrayList<UserEntity> users,
                                   @RequestParam(value = "userLogin") final String userLogin,
                                   @RequestParam(value = "bookId") final String bookId) {

        final ModelAndView modelAndView = new ModelAndView("chooseUser");

        List<UserEntity> resultList = userService.getAllByLogin(userLogin);


        modelAndView.addObject("bookId", bookId);
        modelAndView.addObject("users", resultList);

        return modelAndView;
    }
}
