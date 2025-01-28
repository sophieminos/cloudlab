package com.example.cloudlab.data;

public class Dates {
    private String begin;
    private String end;

    public Dates(String begin, String end){
        this.begin = begin;
        this.end = end;
    }

    public void setBegin(String begin){
        this.begin = begin;
    }

    public void setEnd(String end){
        this.end = end;
    }

    public String getBegin(){
        return this.begin;
    }

    public String getEnd(){
        return this.end;
    }
}
