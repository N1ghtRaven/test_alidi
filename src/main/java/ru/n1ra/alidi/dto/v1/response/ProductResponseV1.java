package ru.n1ra.alidi.dto.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseV1 {
    private Integer id;
    private Integer quantity;
    private Float amount;
}
