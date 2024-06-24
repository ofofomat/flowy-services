package br.edu.catolicasc.flowyservices.entity;

import br.edu.catolicasc.flowyservices.entity.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TasksProjectDTO {

    private String title;

    private String description;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
