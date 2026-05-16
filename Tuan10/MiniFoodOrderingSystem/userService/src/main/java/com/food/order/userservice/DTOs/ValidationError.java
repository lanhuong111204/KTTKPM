package com.food.order.userservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class  ValidationError{
    private String field;
    private String message;
}
