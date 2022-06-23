package com.asn.counter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
