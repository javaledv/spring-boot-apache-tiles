package ml.ledv.spring.boot.apache.tiles.controller;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.BookEntity;
import ml.ledv.spring.boot.apache.tiles.db.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {

    private BookService bookService;

    @Autowired
    public WebController(final BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/")
    public String welcome(){
        return "home";
    }

    @GetMapping("/books")
    public ModelAndView getBooks(){

        final List<BookEntity> books = bookService.getAll();
        final ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("books", books);

        return modelAndView;
    }

    @GetMapping("/books/free")
    public ModelAndView getFreeBooks(){

        final List<BookEntity> books = bookService.getAll();
        final ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("books", null);

        return modelAndView;
    }
}
