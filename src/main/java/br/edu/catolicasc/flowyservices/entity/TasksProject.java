package br.edu.catolicasc.flowyservices.entity;

import br.edu.catolicasc.flowyservices.entity.enums.Priority;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TasksProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tasksProjectId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private Long projectId;

    // Getters and Setters
    public Long getTasksProjectId() {
        return tasksProjectId;
    }

    public void setTasksProjectId(Long tasksProjectId) {
        this.tasksProjectId = tasksProjectId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}