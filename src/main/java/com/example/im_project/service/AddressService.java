package com.example.im_project.service;

import com.example.im_project.controller.form.AddressForm;
import com.example.im_project.domain.Address;
import com.example.im_project.domain.Member;
import com.example.im_project.repository.AddressRepository;
import com.example.im_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    public List<Address> findAddress(String userId) {

        Member findMember = memberRepository.findChkByUserId(userId);
        List<Address> addresses = addressRepository.findAll();

        List<Address> chkAddresses = new ArrayList<>();

        for (Address address : addresses) {
            if (address.getMember().getId() == findMember.getId()) {
                chkAddresses.add(address);
            }
        }

        return chkAddresses;
    }

    @Transactional
    public void addressJoin(AddressForm addressForm, String session_userId) {
        Member member = memberRepository.findByUserId(session_userId).orElse(null);

        Address address = new Address(addressForm);
        address.setMember(member);

        addressRepository.save(address);
    }
}
