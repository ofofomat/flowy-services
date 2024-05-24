package br.edu.catolicasc.flowyservices.entity;

import br.edu.catolicasc.flowyservices.entity.enums.Priority;
import jakarta.persistence.*;

@Entity
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

    // Getters and Setters
    public Long getTasksId() {
        return tasksId;
    }

    public void setTasksId(Long tasksId) {
        this.tasksId = tasksId;
    }

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

    public Long getAreasId() {
        return areasId;
    }

    public void setAreasId(Long areasId) {
        this.areasId = areasId;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}