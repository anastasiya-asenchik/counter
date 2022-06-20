package com.asn.counter;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "value")
    private Long value = 0L;
}
