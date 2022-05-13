package ru.n1ra.alidi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.n1ra.alidi.dto.v1.response.PriceResponseV1;
import ru.n1ra.alidi.service.PriceService;

import java.util.concurrent.TimeUnit;

@Service
public class PriceServiceImpl implements PriceService {

    @Cacheable(value = "price", key = "#productId")
    public PriceResponseV1 getPrice(Integer productId) {
        // Emulate heavy request
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException ignore) {}

        double price = Math.sin(Double.valueOf(productId)) * 1000;
        price = Math.abs(price);

        return new PriceResponseV1(productId, (float) price);
    }

}
