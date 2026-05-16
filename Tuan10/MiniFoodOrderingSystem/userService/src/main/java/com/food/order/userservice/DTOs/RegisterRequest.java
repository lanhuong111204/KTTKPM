package com.food.order.userservice.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username không được để trống")
    @Size(min = 4, message = "Username phải từ 4 ký tự trở lên")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải từ 6 ký tự trở lên")
    private String password;

    @NotBlank(message = "Tên không được để trống")
    private String fullName;

    private String role;
    private String phone;
    private String address;
}
