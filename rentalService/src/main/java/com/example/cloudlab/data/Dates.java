package com.example.cloudlab.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Dates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long car_id;
    private String begin;
    private String end;

    public Dates() {}

    public Dates(Long car_id, String begin, String end) {
        this.car_id = car_id;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }
    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public Long getCar_id() {
        return car_id;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
