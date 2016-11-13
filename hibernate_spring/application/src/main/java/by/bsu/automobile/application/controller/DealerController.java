package by.bsu.automobile.application.controller;

import by.bsu.automobile.persistence.entity.Dealer;
import by.bsu.automobile.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */

@Controller
@RequestMapping("/")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listDealers(ModelMap model) {
        List<Dealer> dealers = dealerService.findAllDealers();
        model.addAttribute("dealers", dealers);
        return "dealers";
    }

    @RequestMapping(value = { "/dealer" }, method = RequestMethod.GET)
    public String newDealer(ModelMap model) {
        Dealer dealer = new Dealer();
        model.addAttribute("dealer", dealer);
        model.addAttribute("edit", false);
        return "registration_dealer";
    }

    @RequestMapping(value = { "/dealer" }, method = RequestMethod.POST)
    public String saveDealer(@Valid Dealer dealer, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration_dealer";
        }

        dealerService.addDealer(dealer);
        model.addAttribute("success", dealer.getName() + " registrated successfully.");
        return "dealers";
    }

    @RequestMapping(value = { "/dealer/{id}" }, method = RequestMethod.GET)
    public String editDealer(@PathVariable int id, ModelMap model) {
        Dealer dealer = dealerService.findById(id);
        model.addAttribute("dealer", dealer);
        model.addAttribute("edit", true);
        return "registration_dealer";
    }

    @RequestMapping(value = { "/dealer/{id}" }, method = RequestMethod.POST)
    public String updateDealer(@Valid Dealer dealer, BindingResult result, ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            return "registration_dealer";
        }

        dealerService.updateDealer(dealer);
        model.addAttribute("success", dealer.getName() + " updated successfully.");
        return "dealers";
    }

    @RequestMapping(value = { "/dealer/{id}" }, method = RequestMethod.DELETE)
    public String deleteDealer(@PathVariable int id) {
        dealerService.deleteDealerById(id);
        return "redirect:/list";
    }
}
