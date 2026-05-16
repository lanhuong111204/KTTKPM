package com.food.order.userservice.controller;

import com.food.order.userservice.DTOs.*;
import com.food.order.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@Valid @RequestBody RegisterRequest req) {
        return ApiResponse.success(userService.register(req), "Đăng ký thành công");
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest req) {
        return ApiResponse.success(userService.login(req), "Đăng nhập thành công");
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUser(@PathVariable Long id) {
        // Dành cho Order Service gọi qua FeignClient/RestTemplate
        return ApiResponse.success(userService.getById(id), "Lấy thông tin thành công");
    }
}
