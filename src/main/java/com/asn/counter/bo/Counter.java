package com.asn.counter.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Counter {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long value = 0L;
}
