package br.com.microservices.authapi.modules.user.controller;

import br.com.microservices.authapi.modules.user.dto.UserRequest;
import br.com.microservices.authapi.modules.user.service.UserService;
import br.com.microservices.authapi.modules.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping("create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        var userResponse = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Override
    @GetMapping("email/{email}")
    public ResponseEntity<UserResponse> findUserByEmail(@PathVariable String email) {
        var userResponse = userService.findByEMail(email);
        return ResponseEntity.ok().body(userResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUser() {
        var userResponse = userService.findAll();
        return ResponseEntity.ok().body(userResponse);
    }
}
