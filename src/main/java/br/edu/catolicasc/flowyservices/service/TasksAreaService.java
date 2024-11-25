package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.TasksArea;
import br.edu.catolicasc.flowyservices.repository.TasksAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksAreaService {

    private final TasksAreaRepository tasksAreaRepository;

    public TasksAreaService(TasksAreaRepository tasksAreaRepository) {
        this.tasksAreaRepository = tasksAreaRepository;
    }

    public List<TasksArea> getAllTasksByAreaId(Long areasId) {
        return tasksAreaRepository.findByAreasId(areasId);
    }

    public Optional<TasksArea> getTaskById(Long tasksId) {
        return tasksAreaRepository.findById(tasksId);
    }

    public TasksArea saveTask(TasksArea task) {
        return tasksAreaRepository.save(task);
    }

    public void deleteTask(Long tasksId) {
        tasksAreaRepository.deleteById(tasksId);
    }
}