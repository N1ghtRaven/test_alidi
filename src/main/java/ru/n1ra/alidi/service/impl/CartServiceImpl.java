package ru.n1ra.alidi.service.impl;

import org.springframework.stereotype.Service;
import ru.n1ra.alidi.dto.v1.request.CartRequestV1;
import ru.n1ra.alidi.dto.v1.request.ProductRequestV1;
import ru.n1ra.alidi.dto.v1.response.CartResponseV1;
import ru.n1ra.alidi.dto.v1.response.PriceResponseV1;
import ru.n1ra.alidi.dto.v1.response.ProductResponseV1;
import ru.n1ra.alidi.dto.Response;
import ru.n1ra.alidi.service.CartService;
import ru.n1ra.alidi.service.PriceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final PriceService priceService;
    public CartServiceImpl(PriceService priceService) {
        this.priceService = priceService;
    }

    public Response<?> calculate(CartRequestV1 cartRequest) {
        List<ProductResponseV1> products = cartRequest.getProducts().stream()
                .map(this::calcProduct)
                .collect(Collectors.toList());

        Double totalAmount = products.stream()
                .mapToDouble(ProductResponseV1::getAmount)
                .sum();

        CartResponseV1 cartResponse = new CartResponseV1(totalAmount.floatValue(), products);
        return new Response<>(cartResponse);
    }

    private ProductResponseV1 calcProduct(ProductRequestV1 productRequest) {
        PriceResponseV1 price = priceService.getPrice(productRequest.getId());
        return ProductResponseV1.builder()
                .id(productRequest.getId())
                .quantity(productRequest.getQuantity())
                .amount(price.getPrice() * productRequest.getQuantity())
                .build();
    }

}
