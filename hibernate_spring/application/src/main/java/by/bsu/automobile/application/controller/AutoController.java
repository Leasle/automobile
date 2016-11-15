package by.bsu.automobile.application.controller;

import by.bsu.automobile.dto.AutoDTO;
import by.bsu.automobile.service.AutoService;
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
@RequestMapping("/autos")
public class AutoController {
    @Autowired
    private AutoService autoService;

    @RequestMapping(value = {"/autos"}, method = RequestMethod.GET)
    public String listAutos(ModelMap model) {
        List<AutoDTO> autos = autoService.findAllAutos();
        model.addAttribute("autos", autos);
        return "autos";
    }

    @RequestMapping(value = { "/auto" }, method = RequestMethod.GET)
    public String newAuto(ModelMap model) {
        AutoDTO auto = new AutoDTO();
        model.addAttribute("auto", auto);
        model.addAttribute("edit", false);
        return "registration_auto";
    }

    @RequestMapping(value = { "/auto" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveAuto(@Valid AutoDTO auto, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration_auto";
        }

        autoService.addAuto(auto);
        model.addAttribute("success", auto.getMark() + " " + auto.getModel() + " registrated successfully.");
        return "autos";
    }

    @RequestMapping(value = { "/auto/{id}" }, method = RequestMethod.GET)
    public String editAuto(@PathVariable int id, ModelMap model) {
        AutoDTO auto = autoService.findAutoById(id);
        model.addAttribute("auto", auto);
        model.addAttribute("edit", true);
        return "registration_auto";
    }

    @RequestMapping(value = { "/auto/{id}" }, method = RequestMethod.POST)
    public String updateAuto(@Valid AutoDTO auto, BindingResult result, ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            return "registration_auto";
        }

        autoService.updateAuto(id, auto);
        model.addAttribute("success", auto.getMark() + " " + auto.getModel() + " updated successfully.");
        return "autos";
    }

    @RequestMapping(value = { "/auto/{id}" }, method = RequestMethod.DELETE)
    public String deleteAuto(@PathVariable int id) {
        autoService.deleteAutoById(id);
        return "redirect:/autos";
    }
}
