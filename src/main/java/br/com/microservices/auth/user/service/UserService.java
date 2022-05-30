package br.com.microservices.auth.user.service;

import br.com.microservices.auth.user.dto.UserRequest;
import br.com.microservices.auth.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse save(UserRequest request);

    UserResponse findByEMail(String email);

    List<UserResponse> findAll();
}
