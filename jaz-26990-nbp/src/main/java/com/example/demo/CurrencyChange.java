package com.example.demo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import jakarta.persistence.*;
@Entity
public class CurrencyChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Numer identyfikacyjny")
    private int ID;
    @Schema(description = "Nazwa waluty")
    private String waluta;
    @Schema(description = "Liczba dni poprzednich kursow waluty")
    private int dni;
    @Schema(description = "Data wykonania zapytania")
    private LocalDate dataWykonania;
    @Schema(description = "Wartosc waluty")
    private Double kurs;

    public CurrencyChange(int ID,String waluta,int dni, LocalDate dataWykonania, Double kurs){
        this.ID = ID;
        this.waluta = waluta;
        this.dni = dni;
        this.dataWykonania = dataWykonania;
        this.kurs = kurs;
    }

    public CurrencyChange() {

    }

    public int getID(){
        return this.ID;
    }
    public String getWaluta(){
        return this.waluta;
    }

    public int getDni(){
        return this.dni;
    }
    public LocalDate getDataWykonania(){
        return this.dataWykonania;
    }

    public Double getKurs() {
        return this.kurs;
    }

    public void setWaluta(String nowaWaluta){
        this.waluta = nowaWaluta;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    public void setDataWykonania(LocalDate nowaDataWykonania){
        this.dataWykonania = nowaDataWykonania;
    }
    public void setKurs(Double nowyKurs){
        this.kurs = nowyKurs;
    }
}
