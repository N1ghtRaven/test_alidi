package ru.n1ra.alidi.dto.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.n1ra.alidi.annotation.StringEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartRequestV1 {
    @NotNull
    @Valid
    private List<ProductRequestV1> products;

    @JsonProperty(value = "address_id")
    @Positive
    private Integer addressId;

    @JsonProperty(value = "payment_type")
    @NotBlank
    @StringEnum(enumClass = PaymentTypeRequestV1.class)
    private String paymentType;
}
