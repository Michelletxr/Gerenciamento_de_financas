package com.imd.financas_api.conta.controller;

import com.imd.financas_api.conta.dto.ContaDTO;
import com.imd.financas_api.conta.service.ContaService;
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
    public ResponseEntity<ContaDTO> save(@RequestBody ContaDTO.RequestConta requestConta){
        ResponseEntity response;
        ContaDTO responseConta = service.Save(requestConta);
        if(!Objects.isNull(responseConta)){
            response = new ResponseEntity<>(responseConta, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar conta", HttpStatus.BAD_REQUEST);
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
            response = new ResponseEntity<>("erro ao solicitar conta com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> findByUser(@PathVariable UUID id){
        ResponseEntity response;
        List<ContaDTO> responseConta = service.findByUser(id);
        if(!responseConta.isEmpty()){
            response = new ResponseEntity<>(responseConta, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar conta com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Conta deletada com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar conta", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
