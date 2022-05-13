package ru.n1ra.alidi.dto.v1.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceResponseV1 {
    @JsonProperty(value = "product_id")
    private Integer productId;
    private Float price;
}
