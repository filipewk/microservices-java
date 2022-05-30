package br.com.microservices.auth.utils;

import br.com.microservices.auth.exception.UserUnauthorizedException;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;

@UtilityClass
public class BcryptUtil {

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public void checkUserPassword(String password, String hashPassword) {
        if (!BCrypt.checkpw(password, hashPassword)) {
            throw new UserUnauthorizedException();
        }
    }
}
