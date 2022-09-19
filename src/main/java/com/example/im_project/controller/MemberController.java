package com.example.im_project.controller;


import com.example.im_project.controller.form.MemberForm;
import com.example.im_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/auth/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "/auth/joinForm";
        }

        memberService.join(form);
        return "/auth/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/auth/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // 외에는 접근이 불가능 함.
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "개인정보";
    }

}
