package com.imd.financas_api.user.repository;
import com.imd.financas_api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByLogin(String login);
}
