package com.imd.financas_api.general.service;

import com.imd.financas_api.conta.dto.ContaDTO;
import com.imd.financas_api.conta.service.ContaService;
import com.imd.financas_api.loan.dto.LoanDTO;
import com.imd.financas_api.loan.dto.ParcelDTO;
import com.imd.financas_api.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeneralService
{
    @Autowired
    ContaService contaService;
    @Autowired
    LoanService loanService;
    public Map<String, List<?>> getGanhos(UUID id){
        Map<String, List<?>> ganhos = new HashMap<>();
        List<ContaDTO> contas = contaService.findByUser(id);
        List<LoanDTO> loans = loanService.findByUser(id);
        ganhos.put("contas", contas);
        ganhos.put("loan", loans);
        return ganhos;
    }
    public Map<UUID, List<ParcelDTO>> getGastos(UUID id){
        List<LoanDTO> loans = loanService.findByUser(id);
        Map<UUID, List<ParcelDTO>> gastos = new HashMap<>();
        loans.forEach(loan -> gastos.put(loan.getId(), loan.getParcelas()));
        return gastos;
    }
}
