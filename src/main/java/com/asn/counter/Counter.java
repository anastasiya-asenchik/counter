package com.asn.counter;

import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Table(name = "counter")
public class Counter {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private Long value = 0L;
}
