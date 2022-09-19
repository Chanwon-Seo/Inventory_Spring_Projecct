package com.example.im_project.controller;

import com.example.im_project.config.auth.PrincipalDetails;
import com.example.im_project.controller.form.AddressForm;
import com.example.im_project.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/address/new/{id}")
    public String addressGET(@PathVariable("id") Long itemId, Model model) {

        AddressForm addressForm = new AddressForm();
        addressForm.setId(itemId);

        model.addAttribute("addressForm", addressForm);
        return "address/addressForm";
    }

    @PostMapping("/address/new/{id}")
    public String addressPOST(@PathVariable("id") Long itemId, @ModelAttribute("addressForm") AddressForm addressForm, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        System.out.println(itemId);
        String username = principalDetails.getUsername();

        addressService.addressJoin(addressForm, username);
        return "redirect:/order/" + itemId;
    }

}
