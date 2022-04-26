package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //JPA 스펙 상 만듬, public 보다 그나마 안전
    protected Address() {
    }
}
