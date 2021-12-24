package com.bekh.streamapitasks;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Student {

    Map<String, Integer> rating;
    String name;

    public Student(String name){
        rating = new HashMap<>();
        this.name = name;
    }

    public Student rate(String subject, Integer rate){
        rating.put(subject, rate);
        return this;
    }
}
