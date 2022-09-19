package com.example.im_project.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InventoryForm {
    //회원이름
    @NotEmpty(message = "정보를 입력해주세요")
    private String name;

    @NotNull(message = "수량을 입력하세요")
    private Integer count;

    @NotNull(message = "수량을 입력하세요")
    private Integer price;
}
