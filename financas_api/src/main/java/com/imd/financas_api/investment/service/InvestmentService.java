package com.imd.financas_api.investment.service;

import com.imd.financas_api.investment.dto.InvestmentDTO;
import com.imd.financas_api.investment.model.Investment;
import com.imd.financas_api.investment.repository.InvestmentRepository;
import com.imd.financas_api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestmentService {
    private final InvestmentRepository repository;

    private final UserRepository userRepository;
    private final InvestmentDTO dto;


    public InvestmentService(InvestmentRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.dto = new InvestmentDTO();
    }

    public List<InvestmentDTO> findAll() {
        List<Investment> investments = repository.findAll();
        return investments.stream().map(investment -> {
            return dto.buildInvestmentToResponseInvestment(investment);
        }).toList();
    }
    public InvestmentDTO findById(UUID id){
        Optional<Investment> investment = repository.findById(id);
        InvestmentDTO responseInvestment = null;

        if(investment.isPresent()){
            responseInvestment = dto.buildInvestmentToResponseInvestment(investment.get());
        }
        return responseInvestment;
    }
    public InvestmentDTO Save(InvestmentDTO.RequestInvestment requestInvestment){
        InvestmentDTO responseInvestment = null;
        if(!Objects.isNull(requestInvestment)){
            //if(verifyUser(requestLoan.login()))
            //{
            Investment investment = new Investment().builder()
                    .id_investment(requestInvestment.id_investment())
                    .corretora(requestInvestment.corretora())
                    .titulo(requestInvestment.titulo())
                    .tipo_titulo(requestInvestment.tipo_titulo())
                    .vencimento_titulo(requestInvestment.vencimento_titulo())
                    .data_investimento(requestInvestment.data_investimento())
                    .investido(requestInvestment.investido())
                    .objetivo(requestInvestment.objetivo())
                    .build();

            Investment createInvestment = repository.save(investment);
            responseInvestment = dto.buildInvestmentToResponseInvestment(createInvestment);
            // }
        }
        return responseInvestment;
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

