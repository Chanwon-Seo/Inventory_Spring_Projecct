package com.example.im_project.repository.querydsl;

import com.example.im_project.domain.Order;
import com.example.im_project.domain.OrderStatus;
import com.example.im_project.domain.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;


    public OrderQueryRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<Order> findAll() {
        /**
         * 생략 가능
         */
//        JPAQueryFactory query = new JPAQueryFactory(em);
//        QOrder order = QOrder.order;
//        QMember member = QMember.member;

        return query
                .select(QOrder.order)
                .from(QOrder.order)
//                .join(QOrder.order.member)
                .where(QOrder.order.status.eq(OrderStatus.ORDER))
//                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

}
