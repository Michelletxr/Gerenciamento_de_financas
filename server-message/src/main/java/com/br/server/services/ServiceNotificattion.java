package com.br.server.services;

import com.br.server.dtos.NotificattionsDto;
import com.br.server.models.NotificattionsMsg;
import com.br.server.repositorys.NotificattionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ServiceNotificattion {
    @Autowired
    NotificattionRepository repository;

    public UUID create(NotificattionsDto msg){
        NotificattionsMsg notificattions = new NotificattionsMsg();
        UUID responseId = null;

        BeanUtils.copyProperties(msg, notificattions);
        notificattions.setDate(LocalDateTime.now());
        NotificattionsMsg createNotificattions = repository.save(notificattions);


        if(Objects.nonNull(createNotificattions)){
            responseId = createNotificattions.getId();
        }
        return responseId;
    }

    public List<NotificattionsDto> findNotificattionByUserName(String userName) {

        List<NotificattionsDto> notificattionsResponse = new ArrayList<>();
        List<NotificattionsMsg> notificattions = repository.findByUsername(userName);

        if(!notificattions.isEmpty()){
            notificattions.forEach(msg -> {
                NotificattionsDto notificattionsDto = new NotificattionsDto();
                BeanUtils.copyProperties(msg, notificattionsDto);
                notificattionsResponse.add(notificattionsDto);
            });
        }

        return notificattionsResponse;
    }
}
