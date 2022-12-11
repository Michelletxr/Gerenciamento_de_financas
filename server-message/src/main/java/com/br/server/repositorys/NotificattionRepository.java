package com.br.server.repositorys;

import com.br.server.models.NotificattionsMsg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificattionRepository extends JpaRepository<NotificattionsMsg, UUID> {
    List<NotificattionsMsg> findByUsername(String userName);
}
