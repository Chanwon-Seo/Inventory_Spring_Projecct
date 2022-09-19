package com.example.im_project.service.admin;


import com.example.im_project.config.auth.PrincipalDetails;
import com.example.im_project.domain.Member;
import com.example.im_project.domain.Order;
import com.example.im_project.repository.MemberRepository;
import com.example.im_project.repository.querydsl.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {
    private final MemberRepository memberRepository;
    private final OrderQueryRepository orderQueryRepository;

    /**
     * Admin에 대한 데이터 가공
     */
    public List<Order> findList(PrincipalDetails principalDetails) {
        Member member = memberRepository.findByUserId(principalDetails.getUsername()).orElse(null);
        if (!member.getRole().equals("ROLE_ADMIN")) {
            System.out.println("ROLE_ADMIN이 아니야");
        }
        List<Order> all = orderQueryRepository.findAll();

        return all;
    }

}
