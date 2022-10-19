package com.imd.financas_api.conta.service;

import com.imd.financas_api.conta.dto.ContaDTO;
import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.conta.repository.ContaRepository;
import com.imd.financas_api.security.JWTConfig;
import com.imd.financas_api.user.dto.UserDTO;
import com.imd.financas_api.user.model.User;
import com.imd.financas_api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService {

    private final ContaRepository repository;
    private final ContaDTO dto;

    private JWTConfig jwtConfig;

    public ContaService(ContaRepository repository){
        this.repository = repository;
        this.dto = new ContaDTO();
        this.jwtConfig = new JWTConfig();
    }

    public List<ContaDTO> findAll() {
        List<Conta> contas = repository.findAll();
        return contas.stream().map(conta -> {
            return dto.buildContaToResponseConta(conta);
        }).toList();
    }
    public ContaDTO findById(UUID id){
        Optional<Conta> conta = repository.findById(id);
        ContaDTO responseConta = null;

        if(conta.isPresent()){
            responseConta = dto.buildContaToResponseConta(conta.get());
        }
        return responseConta;
    }
    public ContaDTO Save(ContaDTO.RequestConta requestConta){
        ContaDTO responseConta = null;
        if(!Objects.isNull(requestConta)){
            {
                Conta conta = new Conta().builder()
                        .name(requestConta.name())
                        .value(requestConta.value())
                        .build();

                Conta createConta = repository.save(conta);
                responseConta = dto.buildContaToResponseConta(createConta);
            }
        }
        return responseConta;
    }


    public boolean delete(UUID id){
        boolean isDelet = false;
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDelet = true;
        }
        return isDelet;
    }

  /*  public boolean verifyUser(String login){
        boolean isValid = false;
        if(Objects.isNull(repository.findByLogin(login))){
            isValid = true;
        }
        return isValid;
    }*/
}
