package com.food.order.userservice.service;

import com.food.order.userservice.DTOs.LoginRequest;
import com.food.order.userservice.DTOs.LoginResponse;
import com.food.order.userservice.DTOs.RegisterRequest;
import com.food.order.userservice.DTOs.UserDTO;
import com.food.order.userservice.entity.Role;
import com.food.order.userservice.entity.User;
import com.food.order.userservice.exception.BusinessException;
import com.food.order.userservice.repository.UserRepository;
import com.food.order.userservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtils jwtUtils;

    public UserDTO register(RegisterRequest req) {
        if(userRepo.existsByUsername(req.getUsername()))
            throw new BusinessException("Username đã tồn tại");

        User user = new User(null, req.getUsername(), encoder.encode(req.getPassword()),
                req.getFullName(), Role.USER, req.getAddress(), req.getPhone());
        user = userRepo.save(user);
        return mapToDTO(user);
    }

    public LoginResponse login(LoginRequest req) {
        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new BusinessException("Tài khoản không tồn tại"));

        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new BusinessException("Mật khẩu không chính xác");

        return LoginResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole().name())
                .token(jwtUtils.generateToken(user))
                .build();
    }

    public UserDTO getById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Không tìm thấy User với ID: " + id));
        return mapToDTO(user);
    }

    private UserDTO mapToDTO(User u) {
        return UserDTO.builder().userId(u.getId()).username(u.getUsername())
                .fullName(u.getFullName()).role(u.getRole().name())
                .phone(u.getPhone()).address(u.getAddress()).build();
    }


}