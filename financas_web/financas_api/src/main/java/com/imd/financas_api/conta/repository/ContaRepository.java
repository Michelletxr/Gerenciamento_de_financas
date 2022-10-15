package com.imd.financas_api.conta.repository;
import com.imd.financas_api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<User, UUID> {
    public User findByLogin(String login);
}

