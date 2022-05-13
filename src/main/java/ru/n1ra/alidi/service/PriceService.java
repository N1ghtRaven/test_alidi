package ru.n1ra.alidi.service;

import ru.n1ra.alidi.dto.v1.response.PriceResponseV1;

public interface PriceService {
    PriceResponseV1 getPrice(Integer productId);
}
