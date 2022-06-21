package com.asn.counter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounterDTO {

    @Id
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    @PositiveOrZero
    private Long value = 0L;
}
