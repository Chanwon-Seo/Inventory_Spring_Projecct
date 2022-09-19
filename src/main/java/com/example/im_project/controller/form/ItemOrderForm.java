package com.example.im_project.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrderForm {

    private Long id;

    private String name;

    private Integer count;

    private Integer price;

    private Integer minusCount;

}
