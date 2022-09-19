package com.example.im_project.controller;

import com.example.im_project.config.auth.PrincipalDetails;
import com.example.im_project.controller.form.OrderForm;
import com.example.im_project.domain.Address;
import com.example.im_project.domain.Order;
import com.example.im_project.service.AddressService;
import com.example.im_project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;
    private final AddressService addressService;

    @GetMapping("/order/{id}")
    public String orderGET(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String session_userId = principalDetails.getUsername();
        List<Address> addresses = addressService.findAddress(session_userId);
        OrderForm orderForm = orderService.selectItem(id);

        model.addAttribute("data", session_userId);
        model.addAttribute("addresses", addresses);
        model.addAttribute("orderForm", orderForm);

        return "order/orderForm";
    }

    @PostMapping("/order/join")
    public String orderPOST(@RequestParam("addressId") Long addressId,
                            @ModelAttribute("orderForm") OrderForm orderForm,
                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String username = principalDetails.getUsername();
        orderService.orderJoin(addressId, orderForm, username);

        return "redirect:/order/orderList";
    }

    @GetMapping("/order/orderList")
    public String OrderFind(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String username = principalDetails.getUsername();
        List<Order> orderLists = orderService.findOrder(username);

        model.addAttribute("orderLists", orderLists);
        return "order/orderList";
    }

}
