package com.bekh.streamapitasks;

import lombok.Data;

@Data
public class Skill {

    private String title;
    private int proficiency;

    public Skill(String title, int proficiency){
        this.title = title;
        this.proficiency = proficiency;
    }
}
