package com.imd.financas_api.loan.service;

import com.imd.financas_api.security.JWTConfig;
import com.imd.financas_api.loan.dto.LoanDTO;
import com.imd.financas_api.loan.model.Loan;
import com.imd.financas_api.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanRepository repository;
    private final LoanDTO dto;

   // private JWTConfig jwtConfig;

    public LoanService(LoanRepository repository){
        this.repository = repository;
        this.dto = new LoanDTO();
        this.jwtConfig = new JWTConfig();
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
        if(!Objects.isNull(requestLoan)){
            //if(verifyUser(requestLoan.login()))
            //{
                Loan loan = new Loan().builder()
                        .descricao(requestLoan.descricao())
                        .valor(requestLoan.valor())
                        .valor_pagar(requestLoan.valor_pagar())
                        .parcelas(requestLoan.parcelas())
                        .tipo_juros(requestLoan.tipo_juros())
                        .juros(requestLoan.juros())
                        .valor_parcela(requestLoan.valor_parcela())
                        .data_inicio(requestLoan.data_inicio())
                        .data_final(requestLoan.data_final())
                        .parcela(requestLoan.parcela())
                        .build();

                Loan createLoan = repository.save(loan);
                responseLoan = dto.buildLoanToResponseLoan(createLoan);
           // }
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

   /* public boolean verifyUser(String login){
        boolean isValid = false;
        if(Objects.isNull(repository.findByLogin(login))){
            isValid = true;
        }
        return isValid;
    }*/
}
