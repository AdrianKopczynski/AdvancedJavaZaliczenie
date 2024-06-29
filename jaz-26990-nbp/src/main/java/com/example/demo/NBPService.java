package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Service
public class NBPService {
    private final RestTemplate restTemplate;

    @Autowired
    public NBPService(RestTemplateBuilder retTemplateBuilder){
        this.restTemplate = retTemplateBuilder.build();
    }

    public Double getAverageExchangeRate(String waluta,int dni){
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/?format=json", waluta, dni);
        ResponseEntity<NBPResponse> response = restTemplate.getForEntity(url, NBPResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getKurs().stream()
                    .mapToDouble(Kurs::getMid)
                    .average()
                    .orElseThrow(() -> new NoSuchElementException("Nie znaleziono kursu"));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono danych na temat waluty");
        }
    }
}
