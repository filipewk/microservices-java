package br.com.microservices.authapi.modules.authuser.service;

import br.com.microservices.authapi.modules.authuser.dto.AuthRequest;
import br.com.microservices.authapi.modules.authuser.dto.AuthResponse;
import br.com.microservices.authapi.exception.UserNotFoundException;
import br.com.microservices.authapi.modules.user.repository.UserRepository;
import br.com.microservices.authapi.modules.user.model.User;
import br.com.microservices.authapi.utils.BcryptUtil;
import br.com.microservices.authapi.modules.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse auth(AuthRequest request) {
        var accessToken = getAccessToken(request);
        return AuthResponse.of(accessToken);
    }


    private String getAccessToken(AuthRequest request) {
        var user = findUserByEmail(request.getEmail());
        BcryptUtil.checkUserPassword(request.getPassword(), user.getPassword());
        return jwtService.getToken(user);
    }

    private User findUserByEmail(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
