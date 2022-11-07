package com.imd.financas_api.investment.controller;

import com.imd.financas_api.investment.dto.InvestmentDTO;
import com.imd.financas_api.investment.service.InvestmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/investimento")
public class InvestmentController {
    private final InvestmentService service;
    private final InvestmentDTO dto;

    public InvestmentController(InvestmentService service){
        this.service = service;
        this.dto = new InvestmentDTO();
    }

    @PostMapping
    public ResponseEntity<InvestmentDTO> save(@RequestBody InvestmentDTO.RequestInvestment requestInvestment){
        ResponseEntity response;
        InvestmentDTO responseInvestment = service.Save(requestInvestment);
        if(!Objects.isNull(responseInvestment)){
            response = new ResponseEntity<>(responseInvestment, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar investimento", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<InvestmentDTO>> findAll(){
        List<InvestmentDTO> investments = service.findAll();
        return new ResponseEntity<>(investments, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        InvestmentDTO responseInvestment = service.findById(id);
        if(!Objects.isNull(responseInvestment)){
            response = new ResponseEntity<>(responseInvestment, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar investimento com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Investimento deletado com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar investimento", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
