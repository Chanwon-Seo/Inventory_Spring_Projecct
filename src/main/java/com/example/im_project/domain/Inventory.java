package com.example.im_project.domain;

import com.example.im_project.controller.form.InventoryForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private Long id;

    private String name;

    @Column(updatable = false)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;
    private int count;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Order order;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        updateDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }

    public Inventory(InventoryForm form, Member chkUserId) {
        this.name = form.getName();
        this.count = form.getCount();
        this.price = form.getPrice();
        this.member = chkUserId;
    }

    public Inventory(String name, int count, int price, Member member) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.member = member;
    }

}
