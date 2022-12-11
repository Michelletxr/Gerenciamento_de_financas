package com.br.server.repositorys;

import com.br.server.models.EmailMsg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailMsg,UUID> {
}
