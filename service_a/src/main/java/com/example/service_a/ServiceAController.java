package com.example.service_a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call")
    public String getData() {
        return callServiceB();
    }

    private String callServiceB() {
        return restTemplate.getForObject("http://service-b:8080/hello",
                String.class);
    }

}
