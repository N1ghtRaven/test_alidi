package ru.n1ra.alidi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response<T> {
    private boolean result;
    private String error;
    private T data;

    public Response(T data) {
        this(true, null, data);
    }
    public static Response<?> error(String error) {
        return new Response<>(false, error, null);
    }

}