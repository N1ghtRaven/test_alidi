package ru.n1ra.alidi.dto.v1.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartResponseV1 {
    @JsonProperty(value = "total_amount")
    private Float totalAmount;
    private List<ProductResponseV1> products;
}
