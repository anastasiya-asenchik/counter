package com.asn.counter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounterDTO {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long value = 0L;
}
