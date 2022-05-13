package ru.n1ra.alidi.service;

import ru.n1ra.alidi.dto.v1.request.CartRequestV1;
import ru.n1ra.alidi.dto.Response;

public interface CartService {
    Response<?> calculate(CartRequestV1 cartRequest);
}
