package com.example.im_project.domain;

import com.example.im_project.controller.form.OrderForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @OneToOne(mappedBy = "orderItem", fetch = FetchType.LAZY)
    private Order order;

    private String itemName;
    private int totalPrice; //주문 가격
    private int count; //주문 수량

    public static OrderItem createOrderItem(Inventory inventory, OrderForm orderForm) {
        OrderItem orderItem = new OrderItem();
        orderItem.itemName = orderForm.getName();
        orderItem.count = orderForm.getAddCount();
        orderItem.totalPrice = orderForm.getAddCount() * inventory.getPrice();

        return orderItem;
    }

}
