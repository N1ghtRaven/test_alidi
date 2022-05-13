package ru.n1ra.alidi.rest.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.n1ra.alidi.dto.v1.request.CartRequestV1;
import ru.n1ra.alidi.dto.Response;
import ru.n1ra.alidi.service.CartService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart")
public class CartControllerV1 {
    private final CartService cartService;
    public CartControllerV1(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/calculate")
    public Response<?> calculateCart(@Valid @RequestBody CartRequestV1 cartRequest) {
        return cartService.calculate(cartRequest);
    }

}
