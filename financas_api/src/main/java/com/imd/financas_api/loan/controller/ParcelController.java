package com.imd.financas_api.loan.controller;

import com.imd.financas_api.loan.dto.ParcelDTO;
import com.imd.financas_api.loan.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/parcela")
public class ParcelController {
    private final ParcelService service;
    private final ParcelDTO dto;

    public ParcelController(ParcelService service){
        this.service = service;
        this.dto = new ParcelDTO();
    }

    @PostMapping
    public ResponseEntity<ParcelDTO> save(@RequestBody ParcelDTO.RequestParcel request){
        ResponseEntity response;
        ParcelDTO responseParcel = service.Save(request);
        if(!Objects.isNull(responseParcel)){
            response = new ResponseEntity<>(responseParcel, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar parcela", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<ParcelDTO>> findAll(){
        List<ParcelDTO> parcels = service.findAll();
        return new ResponseEntity<>(parcels, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        ParcelDTO responseParcel = service.findById(id);
        if(!Objects.isNull(responseParcel)){
            response = new ResponseEntity<>(responseParcel, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar parcela com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Parcela deletada com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar parcela", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
