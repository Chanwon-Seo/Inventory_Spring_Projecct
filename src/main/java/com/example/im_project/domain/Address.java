package com.example.im_project.domain;

import com.example.im_project.controller.form.AddressForm;
import com.example.im_project.controller.form.MemberForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;
    private String city;
    private String street;
    private String zipcode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Order order;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address(AddressForm form) {
        this.city = form.getCity();
        this.street = form.getStreet();
        this.zipcode = form.getZipcode();
    }

    public Address(MemberForm form) {
        this.city = form.getCity();
        this.street = form.getStreet();
        this.zipcode = form.getZipcode();
    }

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getAddresses().add(this);
    }
}
