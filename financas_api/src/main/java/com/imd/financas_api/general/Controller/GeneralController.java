package com.imd.financas_api.general.Controller;

import com.imd.financas_api.general.service.GeneralService;
import com.imd.financas_api.loan.dto.ParcelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/general")
public class GeneralController {
    @Autowired
    GeneralService service;

    @GetMapping(value = "ganhos/{id}")
    public ResponseEntity getGanhos(@PathVariable UUID id){
        ResponseEntity<?> response = null;
        Map<String, List<?>> ganhos = service.getGanhos(id);
        response = new ResponseEntity<>(ganhos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "gastos/{id}")
    public ResponseEntity getGastos(@PathVariable UUID id){
        ResponseEntity<?> response = null;
        Map<UUID, List<ParcelDTO>> ganhos = service.getGastos(id);
        response = new ResponseEntity<>(ganhos, HttpStatus.OK);
        return response;
    }





}
