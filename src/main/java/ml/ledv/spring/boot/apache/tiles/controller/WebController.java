package ml.ledv.spring.boot.apache.tiles.controller;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import ml.ledv.spring.boot.apache.tiles.db.service.BookService;
import ml.ledv.spring.boot.apache.tiles.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class WebController {

    private BookService bookService;
    private UserService userService;

    @Autowired
    public WebController(final BookService bookService,final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String welcome(){
        return "home";
    }

    @GetMapping("/books")
    public ModelAndView getBooks(){

        final List<BookEntity> books = bookService.getAll();
        final ModelAndView modelAndView = new ModelAndView();
        final boolean isFree = false;

        final Map <BookEntity, Boolean> bok = new HashMap<>();
        for (BookEntity bookEntity : books){
            if(userService.getUserByBook(bookEntity).isPresent()){
                bok.put(bookEntity, true);
            }else {
                bok.put(bookEntity, false);
            }
        }

        modelAndView.addObject("books", bok);

        return modelAndView;
    }

    @GetMapping("/books/free")
    public ModelAndView getFreeBooks(){

        final List<BookEntity> books = bookService.getAll();
        final Map<BookEntity, Boolean> freeBooks = new HashMap<>();

        for (BookEntity bookEntity: books){
            Optional<UserEntity> userOptional = userService.getUserByBook(bookEntity);

            if(!userOptional.isPresent()){
                freeBooks.put(bookEntity, false);
            }
        }

        final ModelAndView modelAndView = new ModelAndView();
        final boolean isFree = true;
        modelAndView.addObject("books", freeBooks);
        modelAndView.addObject("isFree", isFree);
        return modelAndView;
    }
}
