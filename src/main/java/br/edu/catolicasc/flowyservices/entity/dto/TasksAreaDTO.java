package br.edu.catolicasc.flowyservices.entity.dto;

import br.edu.catolicasc.flowyservices.entity.enums.Priority;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TasksAreaDTO {

    private String title;
    private String description;
    private String recurrence;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
