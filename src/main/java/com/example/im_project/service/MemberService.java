package com.example.im_project.service;

import com.example.im_project.controller.form.MemberForm;
import com.example.im_project.domain.Address;
import com.example.im_project.domain.Member;
import com.example.im_project.repository.AddressRepository;
import com.example.im_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    private final AddressRepository addressRepository;

    @Transactional
    public void join(MemberForm form) {

        validateDuplicateMember(form); //중복 회원 검증

        Address address = new Address(form);
        Member member = new Member(form);

        member.setRole("ROLE_USER"); //강제 삽입
        String encPassword = bCryptPasswordEncoder.encode(form.getPassword());
        member.setPassword(encPassword);

        address.setMember(member);

        memberRepository.save(member);
        addressRepository.save(address);
    }

    private void validateDuplicateMember(MemberForm form) {

        Optional<Member> findUserId = memberRepository.findByUserId(form.getUserId());

        if (!findUserId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다"); //프론트
        }
    }

}
