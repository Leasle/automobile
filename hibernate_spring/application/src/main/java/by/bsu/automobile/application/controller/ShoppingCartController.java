package by.bsu.automobile.application.controller;

import by.bsu.automobile.dto.ShoppingCartDTO;
import by.bsu.automobile.service.ShoppingCartService;
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
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = {"/shoppingcarts"}, method = RequestMethod.GET)
    public String listShoppingCarts(ModelMap model) {
        List<ShoppingCartDTO> shoppingCarts = shoppingCartService.findAllShoppingCarts();
        model.addAttribute("shoppingCarts", shoppingCarts);
        return "shoppingCarts";
    }

    @RequestMapping(value = {"/shoppingcarts/{idUser}"}, method = RequestMethod.GET)
    public String listShoppingCarts(ModelMap model, @PathVariable int idUser) {
        List<ShoppingCartDTO> shoppingCarts = shoppingCartService.findAllShoppingCartsOfUser(idUser);
        model.addAttribute("shoppingCarts", shoppingCarts);
        return "shoppingCarts";
    }

    @RequestMapping(value = { "/shoppingcart" }, method = RequestMethod.GET)
    public String newShoppingCart(ModelMap model) {
        ShoppingCartDTO shoppingCart = new ShoppingCartDTO();
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("edit", false);
        return "registration_shoppingCart";
    }

    @RequestMapping(value = { "/shoppingcart" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveShoppingCart(@Valid ShoppingCartDTO shoppingCart, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration_shoppingCart";
        }

        shoppingCartService.addShoppingCart(shoppingCart);
        model.addAttribute("success", shoppingCart.getDateTime() + " registrated successfully.");
        return "shoppingCarts";
    }

    @RequestMapping(value = { "/shoppingcart/{id}" }, method = RequestMethod.GET)
    public String editShoppingCart(@PathVariable int id, ModelMap model) {
        ShoppingCartDTO shoppingCart = shoppingCartService.findShoppingCartById(id);
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("edit", true);
        return "registration_shoppingCart";
    }

    @RequestMapping(value = { "/shoppingcart/{id}" }, method = RequestMethod.POST)
    public String updateShoppingCart(@Valid ShoppingCartDTO shoppingCart, BindingResult result, ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            return "registration_shoppingCart";
        }

        shoppingCartService.updateShoppingCart(id, shoppingCart);
        model.addAttribute("success", shoppingCart.getDateTime() + " updated successfully.");
        return "shoppingCarts";
    }

    @RequestMapping(value = { "/shoppingcart/{id}" }, method = RequestMethod.DELETE)
    public String deleteShoppingCart(@PathVariable int id) {
        shoppingCartService.deleteShoppingCartById(id);
        return "redirect:/shoppingCarts";
    }
}
