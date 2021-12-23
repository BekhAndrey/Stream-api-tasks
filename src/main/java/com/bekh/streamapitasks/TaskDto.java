package com.bekh.streamapitasks;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TaskDto {
    private final String id;
    private final String title;
    private final TaskType type;
    private final LocalDate createdOn;
    private Set<String> tags = new HashSet<>();

    public TaskDto(String id, String title, TaskType type, LocalDate createdOn){
        this.id=id;
        this.title=title;
        this.type = type;
        this.createdOn = createdOn;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
