package com.imd.financas_api.spending.service;

import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.conta.repository.ContaRepository;
import com.imd.financas_api.spending.dto.SpendingDto;
import com.imd.financas_api.spending.model.Spending;
import com.imd.financas_api.spending.repository.SpendingRepository;
import com.imd.financas_api.user.model.User;
import com.imd.financas_api.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SpendingService {

    private final SpendingRepository repository;
    private final UserRepository userRepository;
    private final ContaRepository contaRepository;
    private final SpendingDto dto;


    public SpendingService(SpendingRepository repository,
                           UserRepository userRepository, ContaRepository contaRepository){
        this.repository = repository;
        this.dto = new SpendingDto();
        this.userRepository = userRepository;
        this.contaRepository = contaRepository;
    }

    public List<SpendingDto> findAll() {
        List<Spending> spendings = repository.findAll();
        return spendings.stream().map(spending -> {
            return dto.buildSpendingToResponseSpending(spending);
        }).toList();
    }
    public SpendingDto findById(UUID id){
        Optional<Spending> spending = repository.findById(id);
        SpendingDto responseSpending = null;

        if(spending.isPresent()){
           responseSpending  = dto.buildSpendingToResponseSpending(spending.get());
        }
        return responseSpending;
    }

    public SpendingDto Save(SpendingDto.RequestSpending requestSpending){
        SpendingDto responseSpending = null;
        if(!Objects.isNull(responseSpending)){
            Optional<User> user = userRepository.findById(requestSpending.user_id());
            Optional<Conta> conta = contaRepository.findById(requestSpending.account_id());
            if(verifySpending(responseSpending.getName(),
                    user, conta))
            {
                Spending spending = new Spending().builder()
                        .name(requestSpending.name())
                        .value(requestSpending.value())
                        .date_expiration(requestSpending.date_expiration())
                        .paid(requestSpending.paid())
                        .user(user.get())
                        .account(conta.get())
                        .build();
                Spending createSpending = repository.save(spending);
                responseSpending = dto.buildSpendingToResponseSpending(createSpending);
            }
        }
        return responseSpending;
    }

    private boolean verifySpending(String name, Optional<User> user, Optional<Conta> conta) {
        if(user.isPresent() && conta.isPresent()){
            return true;
        }
        return false;
    }

    public boolean delete(UUID id){
        boolean isDelet = false;
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDelet = true;
        }
        return isDelet;
    }

}



