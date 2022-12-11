package com.br.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "test-services")
public class ControllerTestServices {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "test-services")
    public ResponseEntity<String> getRestponseEntity(@RequestBody String entityUrl) {
        return restTemplate.getForEntity(entityUrl, String.class);
    }
}
