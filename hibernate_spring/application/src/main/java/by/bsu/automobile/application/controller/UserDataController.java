package by.bsu.automobile.application.controller;

import by.bsu.automobile.dto.UserDataDTO;
import by.bsu.automobile.service.UserDataService;
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
@RequestMapping("/userdatas")
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = {"/userdatas"}, method = RequestMethod.GET)
    public String listUserDatas(ModelMap model) {
        List<UserDataDTO> userDatas = userDataService.findAllUserDatas();
        model.addAttribute("userDatas", userDatas);
        return "userDatas";
    }

    @RequestMapping(value = { "/userdata" }, method = RequestMethod.GET)
    public String newUserData(ModelMap model) {
        UserDataDTO userData = new UserDataDTO();
        model.addAttribute("userData", userData);
        model.addAttribute("edit", false);
        return "registration_userData";
    }

    @RequestMapping(value = { "/userdata" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveUserData(@Valid UserDataDTO userData, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration_userData";
        }

        userDataService.addUserData(userData);
        model.addAttribute("success", userData.getFirstName() + " " + userData.getLastName() + " registrated successfully.");
        return "userDatas";
    }

    @RequestMapping(value = { "/userdata/{id}" }, method = RequestMethod.GET)
    public String editUserData(@PathVariable int id, ModelMap model) {
        UserDataDTO userData = userDataService.findUserDataById(id);
        model.addAttribute("userData", userData);
        model.addAttribute("edit", true);
        return "registration_userData";
    }

    @RequestMapping(value = { "/userdata/{id}" }, method = RequestMethod.POST)
    public String updateUserData(@Valid UserDataDTO userData, BindingResult result, ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            return "registration_userData";
        }

        userDataService.updateUserData(id, userData);
        model.addAttribute("success", userData.getFirstName() + " " + userData.getLastName() + " updated successfully.");
        return "userDatas";
    }
}
