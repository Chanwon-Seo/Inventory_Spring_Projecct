package com.example.im_project.controller;

import com.example.im_project.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        if (principalDetails != null) {
            model.addAttribute("adminChk", principalDetails.getUsername());
        } else {
            model.addAttribute("adminChk", "null");
        }
        return "index";
    }
}
