package ru.n1ra.alidi.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.n1ra.alidi.ApplicationLoader;
import ru.n1ra.alidi.dto.v1.request.CartRequestV1;
import ru.n1ra.alidi.dto.v1.request.ProductRequestV1;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationLoader.class)
@AutoConfigureMockMvc
class CartControllerV1Test {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public CartControllerV1Test(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void calculateCart_Ok() throws Exception {
        CartRequestV1 request = new CartRequestV1(getValidProductList(), 1, "cash");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                post("/api/v1/cart/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.data.products").isArray())
                .andExpect(jsonPath("$.data.total_amount").isNumber());
    }

    @Test
    void calculateCart_Ok2() throws Exception {
        CartRequestV1 request = new CartRequestV1(getValidProductList(), null, "cash");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.data.products").isArray())
                .andExpect(jsonPath("$.data.total_amount").isNumber());
    }

    @Test
    void calculateCart_InvalidAddress() throws Exception {
        CartRequestV1 request = new CartRequestV1(getValidProductList(), -1, "blah-blah-blah");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void calculateCart_InvalidPaymentType() throws Exception {
        CartRequestV1 request = new CartRequestV1(getValidProductList(), 2, "blah-blah-blah");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void calculateCart_InvalidPaymentType2() throws Exception {
        CartRequestV1 request = new CartRequestV1(getValidProductList(), 2, "    ");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void calculateCart_InvalidPaymentType3() throws Exception {
        CartRequestV1 request = new CartRequestV1(getValidProductList(), 2, "");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void calculateCart_InvalidProduct() throws Exception {
        CartRequestV1 request = new CartRequestV1(getInvalidProductList(), 2, "card");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void calculateCart_ProductsMismatch() throws Exception {
        CartRequestV1 request = new CartRequestV1(null, 2, "card");
        String jsonPayload = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("/api/v1/cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").isBoolean())
                .andExpect(jsonPath("$.error").isString());
    }

    private List<ProductRequestV1> getValidProductList() {
        List<ProductRequestV1> productList = new ArrayList<>();
        productList.add(new ProductRequestV1(1, 2));
        productList.add(new ProductRequestV1(2, 3));
        productList.add(new ProductRequestV1(3, 4));
        productList.add(new ProductRequestV1(4, 5));
        return productList;
    }

    private List<ProductRequestV1> getInvalidProductList() {
        List<ProductRequestV1> productList = new ArrayList<>();
        productList.add(new ProductRequestV1(1, 2));
        productList.add(new ProductRequestV1(-2, 3));
        productList.add(new ProductRequestV1(3, -4));
        productList.add(new ProductRequestV1(-4, null));
        return productList;
    }

}