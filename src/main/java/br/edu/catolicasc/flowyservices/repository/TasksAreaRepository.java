package br.edu.catolicasc.flowyservices.repository;

import br.edu.catolicasc.flowyservices.entity.TasksArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksAreaRepository extends JpaRepository<TasksArea, Long> {
    List<TasksArea> findByAreasId(Long areasId);
}