package com.example.im_project.service;

import com.example.im_project.controller.form.OrderForm;
import com.example.im_project.domain.*;
import com.example.im_project.repository.AddressRepository;
import com.example.im_project.repository.InventoryRepository;
import com.example.im_project.repository.MemberRepository;
import com.example.im_project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final InventoryRepository inventoryRepository;

    private final AddressRepository addressRepository;

    private final OrderRepository orderRepository;

    public OrderForm selectItem(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        OrderForm orderForm = new OrderForm();

        orderForm.setId(inventory.getId());
        orderForm.setName(inventory.getName());
        orderForm.setAddCount(0);

        return orderForm;
    }

    @Transactional
    public void orderJoin(Long addressId, OrderForm orderForm, String username) {
        Inventory findInventory = inventoryRepository.findById(orderForm.getId()).orElse(null);
        Member findMember = memberRepository.findByUserId(username).orElse(null);
        Address findAddress = addressRepository.findById(addressId).orElse(null);

        OrderItem orderItem = OrderItem.createOrderItem(findInventory, orderForm);

        Order order = Order.createOrder(findMember, findAddress, orderItem, findInventory);

        orderRepository.save(order);
    }

    public List<Order> findOrder(String username) {
        List<Order> findOrderByMemberId = orderRepository.findAllByMember_UserId(username);
        return findOrderByMemberId;
    }

    @Transactional
    public void findOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Inventory inventory = inventoryRepository.findById(order.getInventory().getId()).orElse(null);

        inventory.setCount(inventory.getCount() + order.getOrderItem().getCount());
        order.setStatus(OrderStatus.CANCEL);
    }
}
