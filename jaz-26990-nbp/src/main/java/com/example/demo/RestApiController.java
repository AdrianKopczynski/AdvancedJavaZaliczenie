package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/nbp")
public class RestApiController {
    private final NBPService nbpService;
    private final CurrencyChangeRepository currencyChangeRepository;

    @Autowired
    public RestApiController(NBPService nbpService,CurrencyChangeRepository currencyChangeRepository){
        this.nbpService = nbpService;
        this.currencyChangeRepository = currencyChangeRepository;
    }

    @GetMapping("/{waluta}")
    public ResponseEntity<Double> getKursWaluty(
            @PathVariable String waluta,
            @RequestParam(required = false,defaultValue = "1") int dni
    ){
        Double averageRate = nbpService.getAverageExchangeRate(waluta, dni);

        CurrencyChange currencyChange = new CurrencyChange();
        currencyChange.setWaluta(waluta);
        currencyChange.setDni(dni);
        currencyChange.setKurs(averageRate);
        currencyChange.setDataWykonania(LocalDate.now());

        currencyChangeRepository.save(currencyChange);

        return ResponseEntity.ok(averageRate);
    }
}
