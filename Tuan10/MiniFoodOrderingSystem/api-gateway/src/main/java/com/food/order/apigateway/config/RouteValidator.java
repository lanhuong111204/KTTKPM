package com.food.order.apigateway.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    
    public static final List<String> openApiEndpoints = List.of(
            "/api/users/register",
            "/api/users/login"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> {
        String path = request.getURI().getPath();
        HttpMethod method = request.getMethod();

        boolean isOpenApi = openApiEndpoints.stream().anyMatch(path::contains);
        if (isOpenApi) {
            return false; 
        }

        if (path.startsWith("/api/foods") && HttpMethod.GET.equals(method)) {
            return false; 
        }

        return true;
    };
}