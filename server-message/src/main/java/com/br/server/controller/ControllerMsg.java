package com.br.server.controller;

import com.br.server.dtos.EmailDto;
import com.br.server.dtos.NotificattionsDto;
import com.br.server.services.ServiceEmail;
import com.br.server.services.ServiceNotificattion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping
public class ControllerMsg {
    @Autowired
    ServiceEmail serviceEmail;
    @Autowired
    ServiceNotificattion serviceNotificattion;

    @PostMapping("/send-email")
    public ResponseEntity<?> createEmail(@RequestBody @Valid EmailDto msg){
        ResponseEntity<?> response;
        UUID emailId = serviceEmail.sendEmail(msg);
        if(Objects.nonNull(emailId)){
            response = new ResponseEntity<>(emailId, HttpStatus.CREATED);
        }else{
            response = new ResponseEntity<>("erro ao tentar enviar email", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PostMapping("/create-notificattion")
    public ResponseEntity<?> createNotification(@RequestBody @Valid NotificattionsDto msg){
        ResponseEntity<?> response;
        UUID notificattionId = serviceNotificattion.create(msg);
        if(Objects.nonNull(notificattionId)){
            response = new ResponseEntity<>(notificattionId, HttpStatus.CREATED);
        }else{
            response = new ResponseEntity<>("erro ao tentar criar notificação", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/find-notifications/{userName}")
    public ResponseEntity<List<NotificattionsDto>> findNotificattionsByUserName(@PathVariable @Valid String userName){
        List<NotificattionsDto> notificattionsLIst = serviceNotificattion.findNotificattionByUserName(userName);
        ResponseEntity<List<NotificattionsDto>> response =
                new ResponseEntity<List<NotificattionsDto>>(notificattionsLIst, HttpStatus.OK);
        return response;
    }
}
