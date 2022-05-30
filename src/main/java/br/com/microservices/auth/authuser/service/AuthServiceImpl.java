package br.com.microservices.auth.authuser.service;

import br.com.microservices.auth.authuser.dto.AuthRequest;
import br.com.microservices.auth.authuser.dto.AuthResponse;
import br.com.microservices.auth.exception.UserNotFoundException;
import br.com.microservices.auth.user.model.User;
import br.com.microservices.auth.user.repository.UserRepository;
import br.com.microservices.auth.utils.BcryptUtil;
import br.com.microservices.auth.utils.jwt.JwtService;
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
