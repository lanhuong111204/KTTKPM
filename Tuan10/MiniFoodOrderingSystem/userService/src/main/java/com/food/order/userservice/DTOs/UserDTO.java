package com.food.order.userservice.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String fullName;
    private String role;
    private String phone;
    private String address;

}
