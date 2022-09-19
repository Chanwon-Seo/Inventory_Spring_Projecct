package com.example.im_project.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    //회원이름
    @NotEmpty(message = "정보를 입력해주세요")
    private String username;

    //회원정보
    @NotEmpty(message = "정보를 입력해주세요")
    private String userId;

    @NotEmpty(message = "정보를 입력해주세요")
    private String password;

    //주소 정보
    @NotEmpty(message = "정보를 입력해주세요")
    private String city;
    @NotEmpty(message = "정보를 입력해주세요")
    private String zipcode;
    @NotEmpty(message = "정보를 입력해주세요")
    private String street;
}

