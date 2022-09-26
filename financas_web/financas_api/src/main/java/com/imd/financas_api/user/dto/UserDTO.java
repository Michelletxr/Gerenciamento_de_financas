package com.imd.financas_api.user.dto;

import com.imd.financas_api.user.model.User;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {
    private UUID id;
   @NotNull
    private String name;
   @NotNull
    private String login;
    private String password; //encoder
    private String email;

    @Builder
    public UserDTO(UUID id, String name, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public record RequestUser(String name, String login, String password, String email){}

    public record ResponseUser(UUID id, String login, String name, String email){}

    public UserDTO buildUserToResponseUser(User user){
        return new UserDTO().builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();

    }
}
