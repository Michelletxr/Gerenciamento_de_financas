package com.imd.financas_api.loan.service;

import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.conta.repository.ContaRepository;
import com.imd.financas_api.loan.dto.ParcelDTO;
import com.imd.financas_api.loan.model.Loan;
import com.imd.financas_api.loan.model.Parcel;
import com.imd.financas_api.loan.repository.LoanRepository;
import com.imd.financas_api.loan.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final ContaRepository contaRepository;
    private final LoanRepository loanRepository;
    private final ParcelDTO parcelDTO;

    public ParcelService(ParcelRepository parcelRepository, ContaRepository contaRepository, LoanRepository loanRepository) {
        this.parcelRepository = parcelRepository;
        this.contaRepository = contaRepository;
        this.loanRepository = loanRepository;
        this.parcelDTO = new ParcelDTO();
    }


    public List<ParcelDTO> findAll() {
        List<Parcel> parcels = parcelRepository.findAll();
        return parcels.stream().map(parcel -> {
            return parcelDTO.buildParcelToResponseLoan(parcel);
        }).toList();
    }
    public ParcelDTO findById(UUID id){
        Optional<Parcel> parcel =  parcelRepository.findById(id);
        ParcelDTO response = null;

        if(parcel.isPresent()){
            response = parcelDTO.buildParcelToResponseLoan(parcel.get());
        }
        return response;
    }
    public ParcelDTO Save(ParcelDTO.RequestParcel request){
        ParcelDTO response = null;
        Optional<Conta> conta  = contaRepository.findById(request.conta_id());
        Optional<Loan> emprestimo = loanRepository.findById(request.emprestimo_id());
        if(conta.isPresent() && emprestimo.isPresent()){
            Parcel parcel = new Parcel().builder()
                    .description(request.descricao())
                    .value(request.valor())
                    .status(request.status())
                    .data_vencimento(request.data_vencimento())
                    .data_pagamento(request.data_pagamento())
                    .conta(conta.get())
                    .emprestimo(emprestimo.get())
                    .build();

            Parcel create = parcelRepository.save(parcel);
            response = parcelDTO.buildParcelToResponseLoan(create);
        }
        return response;
    }

    public boolean delete(UUID id){
        boolean isDelet = false;
        if(parcelRepository.findById(id).isPresent()) {
            parcelRepository.deleteById(id);
            isDelet = true;
        }
        return isDelet;
    }
}
