package br.com.microservices.authapi.modules.user.service;

import br.com.microservices.authapi.exception.UserNotFoundException;
import br.com.microservices.authapi.modules.user.dto.UserRequest;
import br.com.microservices.authapi.modules.user.repository.UserRepository;
import br.com.microservices.authapi.modules.user.dto.UserResponse;
import br.com.microservices.authapi.modules.user.model.User;
import br.com.microservices.authapi.utils.BcryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse save(UserRequest request) {
        preSave(request);
        var user = userRepository.save(User.of(request));
        return UserResponse.of(user);
    }

    @Override
    public UserResponse findByEMail(String email) {
        return UserResponse.of(findUserByEmail(email));
    }

    @Override
    public List<UserResponse> findAll() {
        var users = userRepository.findAll();
        return UserResponse.ofList(users);
    }

    private void preSave(UserRequest request) {
        request.setPassword(BcryptUtil.hash(request.getPassword()));
    }

    private User findUserByEmail(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
