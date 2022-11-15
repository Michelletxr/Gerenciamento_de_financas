package com.imd.financas_api.user.dto;

import com.imd.financas_api.user.model.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Component
public class UserDTO {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String login;
    private String password; //encoder
    private String email;
    private List<UUID> accounts;

    @Builder
    public UserDTO(UUID id, String name, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
       // this.accounts = accounts;
    }

    public record RequestUser(String name, String login, String password, String email){}

    public record ResponseUser(UUID id, String login, String name, String email){}

    public static UserDTO buildUserToResponseUser(User user){

       // List<UUID> accounts_id = new ArrayList<>();
        //user.getContas().stream().map(c-> accounts_id.add(c.getId()));

        return new UserDTO().builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                //.accounts(accounts_id)
                .build();

    }
}