package com.example.im_project.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateForm {

    private Long id;

    private String name;

    private Integer count;

    private Integer price;
}
