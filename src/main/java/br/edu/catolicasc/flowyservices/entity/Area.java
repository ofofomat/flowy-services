package br.edu.catolicasc.flowyservices.entity;

import jakarta.persistence.*;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areasId;

    @Column(length = 50, nullable = false)
    private String name;

    // Getters and Setters
    public Long getAreasId() {
        return areasId;
    }

    public void setAreasId(Long areasId) {
        this.areasId = areasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}