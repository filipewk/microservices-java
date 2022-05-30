package br.com.microservices.auth.user.model;

import br.com.microservices.auth.user.dto.UserRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotBlank
    private String password;

    public static User of(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
