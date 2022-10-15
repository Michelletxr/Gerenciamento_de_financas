package com.imd.financas_api.spending.controller;

import com.imd.financas_api.spending.dto.SpendingDto;
import com.imd.financas_api.spending.service.SpendingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("api/spending")
public class SpendingController {
    private final SpendingService service;
    private final SpendingDto dto;

    public SpendingController(SpendingService service){
        this.service = service;
        this.dto = new SpendingDto();
    }

    @PostMapping
    public ResponseEntity<SpendingDto> save(@RequestBody SpendingDto.RequestSpending requestSpending){
        ResponseEntity response;
        SpendingDto responseSpending = service.Save(requestSpending);

        if(!Objects.isNull(responseSpending)){
            response = new ResponseEntity<>(responseSpending, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar dívida", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<SpendingDto>> findAll(){
        List<SpendingDto> spendings = service.findAll();
        return new ResponseEntity<>(spendings, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        SpendingDto responseSpending = service.findById(id);
        if(!Objects.isNull(responseSpending)){
            response = new ResponseEntity<>(responseSpending, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar dívida com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Dívida deletado com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar dívida", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
