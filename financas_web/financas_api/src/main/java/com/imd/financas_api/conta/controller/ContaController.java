package com.imd.financas_api.conta.controller;

import com.imd.financas_api.conta.dto.ContaDTO;
import com.imd.financas_api.conta.service.ContaService;
import com.imd.financas_api.user.dto.UserDTO;
import com.imd.financas_api.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("api/conta")
public class ContaController {

    private final ContaService service;
    private final ContaDTO dto;

    public ContaController(ContaService service){
        this.service = service;
        this.dto = new ContaDTO();
    }

    @PostMapping
    public ResponseEntity<ContaDTO> save(@RequestBody ContaDTO.RequestConta conta){
        ResponseEntity response;
        ContaDTO responseConta = service.Save(conta);
        if(!Objects.isNull(responseConta)){
            response = new ResponseEntity<>(responseConta, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar usuário", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll(){
        List<ContaDTO> contas = service.findAll();
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        ContaDTO responseConta = service.findById(id);
        if(!Objects.isNull(responseConta)){
            response = new ResponseEntity<>(responseConta, HttpStatus.OK);
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
}
