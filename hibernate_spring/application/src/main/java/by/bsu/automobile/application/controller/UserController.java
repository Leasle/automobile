package by.bsu.automobile.application.controller;

import by.bsu.automobile.dto.UserDTO;
import by.bsu.automobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration_user";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveUser(@Valid UserDTO user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration_user";
        }

        userService.addUser(user);
        model.addAttribute("success", user.getLogin() + " registrated successfully.");
        return "users";
    }

    @RequestMapping(value = { "/user/{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        UserDTO user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration_user";
    }

    @RequestMapping(value = { "/user/{id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid UserDTO user, BindingResult result, ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            return "registration_user";
        }

        userService.updateUser(id, user);
        model.addAttribute("success", user.getLogin() + " updated successfully.");
        return "users";
    }

    @RequestMapping(value = { "/user/{id}" }, method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
