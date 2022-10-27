package com.imd.financas_api.loan.controller;

import com.imd.financas_api.loan.dto.LoanDTO;
import com.imd.financas_api.loan.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/emprestimo")
public class LoanController {
    private final LoanService service;
    private final LoanDTO dto;

    public LoanController(LoanService service){
        this.service = service;
        this.dto = new LoanDTO();
    }

    @PostMapping
    public ResponseEntity<LoanDTO> save(@RequestBody LoanDTO.RequestLoan requestLoan){
        ResponseEntity response;
        LoanDTO responseLoan = service.Save(requestLoan);
        if(!Objects.isNull(responseLoan)){
            response = new ResponseEntity<>(responseLoan, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar usuário", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAll(){
        List<LoanDTO> loans = service.findAll();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        LoanDTO responseLoan = service.findById(id);
        if(!Objects.isNull(responseLoan)){
            response = new ResponseEntity<>(responseLoan, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar empréstimo com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Empréstimo deletado com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar empréstimo", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
