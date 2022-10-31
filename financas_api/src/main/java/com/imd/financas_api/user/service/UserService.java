package com.imd.financas_api.user.service;

import com.imd.financas_api.user.dto.UserDTO;
import com.imd.financas_api.user.model.User;
import com.imd.financas_api.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserDTO dto;

    private PasswordEncoder encoder;

    public UserService(UserRepository repository){
        this.repository = repository;
        this.dto = new UserDTO();
    }

    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(user -> {
            return dto.buildUserToResponseUser(user);
        }).toList();
    }
    public UserDTO findById(UUID id){
        Optional<User> user = repository.findById(id);
        UserDTO responseUser = null;

        if(user.isPresent()){
            responseUser = dto.buildUserToResponseUser(user.get());
        }
        return responseUser;
    }
    public UserDTO Save(UserDTO.RequestUser requestUser){
        UserDTO responseUser = null;
        if(!Objects.isNull(requestUser)){
            if(verifyUser(requestUser.login()))
            {
                User user = new User().builder()
                        .name(requestUser.name())
                        .login(requestUser.login())
                        .password(encoder.encode(requestUser.password()))
                        .email(requestUser.email())
                        .build();

                User createUser = repository.save(user);
                responseUser = dto.buildUserToResponseUser(createUser);
            }
        }
        return responseUser;
    }

    public boolean delete(UUID id){
        boolean isDelet = false;
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDelet = true;
        }
        return isDelet;
    }

    public boolean verifyUser(String login){
        boolean isValid = false;
        if(Objects.isNull(repository.findByLogin(login))){
            isValid = true;
        }
        return isValid;
    }

    public UserDTO gerUserByLogin(String login, String password){
        UserDTO userDTO = null;
        Optional<User> user = repository.findByLogin(login);
        if(user.isPresent()){
           String passwordEncoder = encoder.encode(password);
           if(passwordEncoder.equals(password)) {userDTO = UserDTO.buildUserToResponseUser(user.get());}
        }
        return userDTO;
    }
}
