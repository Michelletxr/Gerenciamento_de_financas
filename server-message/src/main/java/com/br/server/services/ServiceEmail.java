package com.br.server.services;

import com.br.server.dtos.EmailDto;
import com.br.server.models.EmailMsg;
import com.br.server.models.StatusEmail;
import com.br.server.repositorys.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ServiceEmail {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public UUID sendEmail(EmailDto emailDto){
        EmailMsg email = new EmailMsg();
        email.setSendDateEmail(LocalDateTime.now());
        UUID id = null;

        BeanUtils.copyProperties(emailDto, email);
        EmailMsg emailCreate = new EmailMsg();

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailDto.getEmailFrom());
            message.setTo(emailDto.getEmailTo());
            message.setSubject(emailDto.getTitle());
            message.setText(emailDto.getText());
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SEND);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailCreate = emailRepository.save(email);
        }

        if(emailCreate.getStatusEmail().equals(StatusEmail.SEND)){
            id = emailCreate.getId();
        }

        return id;
    }

}
