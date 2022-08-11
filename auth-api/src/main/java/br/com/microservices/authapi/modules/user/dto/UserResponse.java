package br.com.microservices.authapi.modules.user.dto;

import br.com.microservices.authapi.modules.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static List<UserResponse> ofList(List<User> users) {
        List<UserResponse> usersReponse = new ArrayList<>();
        users.forEach(user -> usersReponse.add(of(user)));
        return usersReponse;
    }
}
