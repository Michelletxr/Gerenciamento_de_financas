package com.imd.financas_api.conta.repository;
import com.imd.financas_api.conta.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
    //public Conta findByLogin(String login);
}
