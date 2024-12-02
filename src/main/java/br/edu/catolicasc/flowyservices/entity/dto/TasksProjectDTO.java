package br.edu.catolicasc.flowyservices.entity.dto;

import br.edu.catolicasc.flowyservices.entity.enums.Priority;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TasksProjectDTO {

    private String title;
    private String description;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
