package br.edu.catolicasc.flowyservices.repository;

import br.edu.catolicasc.flowyservices.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProjectCheckTrue();
    List<Project> findByProjectCheckFalse();
}