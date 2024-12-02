package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.TasksProject;
import br.edu.catolicasc.flowyservices.repository.TasksProjectRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksProjectService {

    private final TasksProjectRepository tasksProjectRepository;

    public TasksProjectService(TasksProjectRepository tasksProjectRepository) {
        this.tasksProjectRepository = tasksProjectRepository;
    }

    @Cacheable(value = "tasksByProjectId", key = "#projectId")
    public List<TasksProject> getTasksByProjectId(Long projectId) {
        return tasksProjectRepository.findByProjectId(projectId);
    }

    @Cacheable(value = "taskById", key = "#id")
    public Optional<TasksProject> getTaskById(Long id) {
        return tasksProjectRepository.findById(id);
    }

    public TasksProject saveTask(TasksProject tasksProject) {
        return tasksProjectRepository.save(tasksProject);
    }

    public void deleteTask(Long id) {
        tasksProjectRepository.deleteById(id);
    }
}