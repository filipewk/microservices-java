package br.com.microservices.authapi.modules.user.controller;

import br.com.microservices.authapi.modules.user.dto.UserRequest;
import br.com.microservices.authapi.modules.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<UserResponse> createUser(UserRequest request);

    ResponseEntity<UserResponse> findUserByEmail(String email);

    ResponseEntity<List<UserResponse>> findAllUser();
}
