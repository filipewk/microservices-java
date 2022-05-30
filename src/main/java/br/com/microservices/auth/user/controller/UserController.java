package br.com.microservices.auth.user.controller;

import br.com.microservices.auth.user.dto.UserRequest;
import br.com.microservices.auth.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<UserResponse> createUser(UserRequest request);

    ResponseEntity<UserResponse> findUserByEmail(String email);

    ResponseEntity<List<UserResponse>> findAllUser();
}
