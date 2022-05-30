package br.com.microservices.auth.user.controller;

import br.com.microservices.auth.user.dto.UserRequest;
import br.com.microservices.auth.user.dto.UserResponse;
import br.com.microservices.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
