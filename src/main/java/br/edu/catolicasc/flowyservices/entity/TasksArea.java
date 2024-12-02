package br.edu.catolicasc.flowyservices.entity;

import br.edu.catolicasc.flowyservices.entity.enums.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tasksId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private Long areasId;

    @Column(length = 100)
    private String recurrence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
}
