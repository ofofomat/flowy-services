package br.edu.catolicasc.flowyservices.repository;

import br.edu.catolicasc.flowyservices.entity.TasksProject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TasksProjectRepository extends JpaRepository<TasksProject, Long> {
    List<TasksProject> findByProjectId(Long projectId);
}
