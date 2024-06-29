package com.example.demo;
import java.util.List;
public class NBPResponse {
    private List<Kurs> kurs;
    public List<Kurs> getKurs(){
        return kurs;
    }
    public void setKurs(List<Kurs> kurs){
        this.kurs = kurs;
    }
}
