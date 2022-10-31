package com.imd.financas_api.user.controller;

import com.imd.financas_api.user.dto.UserDTO;
import com.imd.financas_api.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService service;
    private final UserDTO dto;

    public UserController(UserService service){
       this.service = service;
       this.dto = new UserDTO();
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO.RequestUser user){
        ResponseEntity response;
        UserDTO responseUser = service.Save(user);
        if(!Objects.isNull(responseUser)){
            response = new ResponseEntity<>(responseUser, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar usuário", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        UserDTO responseUser = service.findById(id);
        if(!Objects.isNull(responseUser)){
            response = new ResponseEntity<>(responseUser, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar usuário com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar usuário", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /*
    @PostMapping(value= "/log")
    public ResponseEntity<?> loggerUser(@RequestBody UserDTO.RequestUser user){
        ResponseEntity response;
        UserDTO userDTO = service.gerUserByLogin(user.login(), user.password());
        if(!Objects.isNull(userDTO)){
            response = new ResponseEntity<>(userDTO, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("Credenciais inválidas!", HttpStatus.BAD_REQUEST);
        }
        return response;
    }*/
}
