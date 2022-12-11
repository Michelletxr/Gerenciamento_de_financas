package com.imd.financas_api.conta.service;

import com.imd.financas_api.conta.dto.ContaDTO;
import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.conta.repository.ContaRepository;
import com.imd.financas_api.user.model.User;
import com.imd.financas_api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService {

    private final ContaRepository repository;
    private final UserRepository userRepository;
    private final ContaDTO dto;

   // private JWTConfig jwtConfig;

    public ContaService(ContaRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.dto = new ContaDTO();
       // this.jwtConfig = new JWTConfig();
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
        Optional<User> user = userRepository.findById(UUID.fromString(requestConta.user_id()));
        if(user.isPresent()){
            {
                Conta conta = new Conta().builder()
                        .name(requestConta.name())
                        .value(requestConta.value())
                        .user(user.get())
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

    public ContaDTO findByUser(UUID id) {
        Optional<Conta> conta = repository.findByUserId(id);
        ContaDTO responseConta = null;
        if(conta.isPresent()){
            responseConta = dto.buildContaToResponseConta(conta.get());
        }
        return responseConta;
    }
}
