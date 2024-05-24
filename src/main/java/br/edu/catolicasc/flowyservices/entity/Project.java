package br.edu.catolicasc.flowyservices.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean projectCheck;

    // Getters and Setters
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProjectCheck() {
        return projectCheck;
    }

    public void setProjectCheck(Boolean projectCheck) {
        this.projectCheck = projectCheck;
    }
}