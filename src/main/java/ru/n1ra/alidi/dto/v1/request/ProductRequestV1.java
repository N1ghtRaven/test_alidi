package ru.n1ra.alidi.dto.v1.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class ProductRequestV1 {
    @Positive
    @NotNull
    private Integer id;

    @Positive
    @NotNull
    private Integer quantity;
}
