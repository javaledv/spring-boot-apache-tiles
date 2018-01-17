package ml.ledv.spring.boot.apache.tiles.controller;

import ml.ledv.spring.boot.apache.tiles.db.entity.impl.UserEntity;
import ml.ledv.spring.boot.apache.tiles.db.service.BookService;
import ml.ledv.spring.boot.apache.tiles.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private BookService bookService;

    @Autowired
    public UserController(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping()
    public ModelAndView getUsers() {

        final ModelAndView modelAndView = new ModelAndView("getUsers");
        final List<UserEntity> users = userService.getAll();

        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("userEntity") final UserEntity userEntity) {

        final String userLogin = userEntity.getLogin();
        if (userLogin.length() < 1) {
            final ModelAndView modelAndView = new ModelAndView("redirect:/users/add/form");
            modelAndView.addObject("error", "Empty field login!");
            return modelAndView;
        } else {
            userService.createUser(userLogin);
        }

        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/add/form")
    public ModelAndView addUserForm(@RequestParam(value = "error", required = false) final String error) {

        final ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("userEntity", new UserEntity());
        modelAndView.addObject("error", error);

        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView removeUser(@RequestParam(value = "userId") final String userId) {

        final Optional<UserEntity> userOptional = userService.getUserById(userId);

        if (!userOptional.isPresent()) {
            final ModelAndView errorModelEndView = new ModelAndView("error");
            errorModelEndView.addObject("error", "User with id " + userId + " is not exist!");

            return errorModelEndView;
        } else {
            final ModelAndView modelAndView = new ModelAndView("redirect:/users");

            userService.deleteUser(userOptional.get());
            return modelAndView;
        }
    }

    @GetMapping("/search")
    public ModelAndView findUsers(@RequestParam(value = "userLogin") final String userLogin) {

        final List<UserEntity> users = userService.getAllByLogin(userLogin);
        final ModelAndView modelAndView = new ModelAndView("getUsers");

        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
