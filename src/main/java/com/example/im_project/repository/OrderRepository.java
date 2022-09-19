package com.example.im_project.repository;

import com.example.im_project.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findAllByMember_UserId(String username) ;
}
