package com.food.order.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "UserId không được để trống")
    private Long userId;

    @NotEmpty(message = "Danh sách món ăn không được để trống")
    @Valid
    private List<OrderItemRequest> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderItemRequest {

        @NotNull(message = "FoodId không được để trống")
        private Long foodId;

        @NotNull(message = "Số lượng không được để trống")
        @Min(value = 1, message = "Số lượng món ăn ít nhất phải là 1")
        private Integer quantity;
    }
}
