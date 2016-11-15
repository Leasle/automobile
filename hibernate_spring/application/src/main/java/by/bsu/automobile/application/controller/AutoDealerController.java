package by.bsu.automobile.application.controller;

import by.bsu.automobile.dto.AutoDealerDTO;
import by.bsu.automobile.dto.DealerDTO;
import by.bsu.automobile.service.AutoDealerService;
import by.bsu.automobile.service.DealerService;
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
@RequestMapping("/autodealers")
public class AutoDealerController {
    @Autowired
    private AutoDealerService autoDealerService;

    @RequestMapping(value = {"/autodealers"}, method = RequestMethod.GET)
    public String listAutoDealers(ModelMap model) {
        List<AutoDealerDTO> autoDealers = autoDealerService.findAllAutoDealers();
        model.addAttribute("autoDealers", autoDealers);
        return "autoDealers";
    }

    @RequestMapping(value = {"/autodealers/{mark}"}, method = RequestMethod.GET)
    public String listAutoDealers(ModelMap model, @PathVariable String mark) {
        List<AutoDealerDTO> autoDealers = autoDealerService.findAllAutoDealersByMark(mark);
        model.addAttribute("autoDealers", autoDealers);
        return "autoDealers";
    }

    @RequestMapping(value = {"/autodealers/{idUser}"}, method = RequestMethod.GET)
    public String listAutoDealers(ModelMap model, @PathVariable int idUser) {
        List<AutoDealerDTO> autoDealers = autoDealerService.findAllAutoDealersOfUser(idUser);
        model.addAttribute("autoDealers", autoDealers);
        return "autoDealers";
    }

    @RequestMapping(value = { "/autodealer" }, method = RequestMethod.GET)
    public String newAutoDealer(ModelMap model) {
        AutoDealerDTO autoDealer = new AutoDealerDTO();
        model.addAttribute("autoDealer", autoDealer);
        model.addAttribute("edit", false);
        return "registration_autoDealer";
    }

    @RequestMapping(value = { "/autodealer" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveAutoDealer(@Valid AutoDealerDTO autoDealer, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration_autoDealer";
        }

        autoDealerService.addAutoDealer(autoDealer);
        model.addAttribute("success", autoDealer.getCost() + " registrated successfully.");
        return "autoDealers";
    }

    @RequestMapping(value = { "/autodealer/{idDealer}/{idAuto}" }, method = RequestMethod.GET)
    public String editAutoDealer(@PathVariable int idDealer, @PathVariable int idAuto, ModelMap model) {
        AutoDealerDTO autoDealer = autoDealerService.findAutoDealerById(idAuto, idDealer);
        model.addAttribute("autoDealer", autoDealer);
        model.addAttribute("edit", true);
        return "registration_autoDealer";
    }

    @RequestMapping(value = { "/autodealer/{idDealer}/{idAuto}" }, method = RequestMethod.POST)
    public String updateAutoDealer(@Valid AutoDealerDTO autoDealer, BindingResult result, ModelMap model,
                                   @PathVariable int idDealer, @PathVariable int idAuto) {
        if (result.hasErrors()) {
            return "registration_autoDealer";
        }

        autoDealerService.updateAutoDealer(idAuto, idDealer, autoDealer);
        model.addAttribute("success", autoDealer.getCost() + " updated successfully.");
        return "autoDealers";
    }

    @RequestMapping(value = { "/autodealer/{idDealer}/{idAuto}" }, method = RequestMethod.DELETE)
    public String deleteAutoDealer(@PathVariable int idDealer, @PathVariable int idAuto) {
        autoDealerService.deleteAutoDealerById(idAuto, idDealer);
        return "redirect:/autodealers";
    }
}
