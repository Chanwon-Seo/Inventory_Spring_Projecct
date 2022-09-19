package com.example.im_project.domain;

import com.example.im_project.config.auth.PrincipalDetails;
import com.example.im_project.controller.form.MemberForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String userId;
    private String password;
    private String role; //ROLE_USER, ROLE_ADMIN

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Inventory> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Address> addresses = new ArrayList<>();


    public Member(MemberForm form) {
        this.username = form.getUsername();
        this.userId = form.getUserId();
        this.password = form.getPassword();
    }

    public Member(String username, String userId, String password, String role) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public Member(String username, String userId, String password) {
        this.username = username;
        this.userId = userId;
        this.password = password;
    }
}
