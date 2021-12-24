package com.bekh.streamapitasks;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Task {
    private final String id;
    private final String title;
    private final TaskType type;
    private final LocalDate createdOn;
    private boolean done = false;
    private Set<String> tags = new HashSet<>();
    private LocalDate dueTo;

//    public Task(String id, String title, TaskType type, LocalDate createdOn){
//        this.id=id;
//        this.title=title;
//        this.type = type;
//        this.createdOn = createdOn;
//    }

    public Task addTag(String tag){
        tags.add(tag);
        return this;
    }
}