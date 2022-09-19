package com.example.im_project.controller;

import com.example.im_project.config.auth.PrincipalDetails;
import com.example.im_project.domain.Order;
import com.example.im_project.service.OrderService;
import com.example.im_project.service.admin.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class adminController {

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/orderList")
    public String adminOrderGET(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        List<Order> adminOrderList = orderQueryService.findList(principalDetails);

        model.addAttribute("orderLists", adminOrderList);
        return "admin/adminOrderList";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/orderList/{id}")
    public String adminOrderPOST(@PathVariable("id") Long orderId) {
        System.out.println("도착함");
        orderService.findOrderId(orderId);

        return "redirect:/admin/orderList";
    }

}
