package com.food.order.userservice.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.time.ZonedDateTime;
import java.util.List;
@Data@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String timestamp;
    private boolean success;
    private int code;
    private String message;
    private T data;
    private List<ValidationError> errors;


    public static <T> ApiResponse<T> success (T data , String msg){
        return ApiResponse.<T>builder()
                .timestamp(ZonedDateTime.now().toString())
                .success(true)
                .code(200)
                .message(msg)
                .data(data)
                .build();

    }
}

