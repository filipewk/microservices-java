package br.com.microservices.authapi.modules.user.service;

import br.com.microservices.authapi.modules.user.dto.UserRequest;
import br.com.microservices.authapi.modules.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse save(UserRequest request);

    UserResponse findByEMail(String email);

    List<UserResponse> findAll();
}
