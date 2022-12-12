package com.imd.financas_api.loan.service;

import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.conta.repository.ContaRepository;
import com.imd.financas_api.loan.dto.LoanDTO;
import com.imd.financas_api.loan.model.Loan;
import com.imd.financas_api.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanRepository repository;
    private final ContaRepository contaRepository;
    private final LoanDTO dto;

    public LoanService(LoanRepository repository, ContaRepository contaRepository){
        this.repository = repository;
        this.contaRepository = contaRepository;
        this.dto = new LoanDTO();
    }

    public List<LoanDTO> findAll() {
        List<Loan> loans = repository.findAll();
        return loans.stream().map(loan -> {
            return dto.buildLoanToResponseLoan(loan);
        }).toList();
    }
    public LoanDTO findById(UUID id){
        Optional<Loan> loan = repository.findById(id);
        LoanDTO responseLoan = null;

        if(loan.isPresent()){
            responseLoan = dto.buildLoanToResponseLoan(loan.get());
        }
        return responseLoan;
    }
    public LoanDTO Save(LoanDTO.RequestLoan requestLoan){
        LoanDTO responseLoan = null;
        Optional<Conta> conta  = contaRepository.findById(requestLoan.conta_id());
        if(conta.isPresent()){
                Loan loan = new Loan().builder()
                        .descricao(requestLoan.descricao())
                        .valor(requestLoan.valor())
                        .valor_pagar(requestLoan.valor_pagar())
                       // .parcels(requestLoan.parcelas())
                        .tipo_juros(requestLoan.tipo_juros())
                        .juros(requestLoan.juros())
                        .data_inicio(requestLoan.data_inicio())
                        .data_final(requestLoan.data_final())
                        .conta(conta.get())
                        .build();

                Loan createLoan = repository.save(loan);
                responseLoan = dto.buildLoanToResponseLoan(createLoan);
        }
        return responseLoan;
    }

    public boolean delete(UUID id){
        boolean isDelet = false;
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDelet = true;
        }
        return isDelet;
    }

    public List<LoanDTO> findByUser(UUID id) {
        List<Loan> list = repository.findAll();
        List<LoanDTO> responseLoan = new ArrayList<>();
        if(!list.isEmpty()){
            list.forEach(loan -> {
                if(id.equals(loan.getConta().getUser().getId())){
                    responseLoan.add(dto.buildLoanToResponseLoan(loan));
                }
            });
        }
        return responseLoan;
    }

}
