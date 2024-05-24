package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.TasksArea;
import br.edu.catolicasc.flowyservices.repository.TasksAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksAreaService {

    @Autowired
    private TasksAreaRepository tasksAreaRepository;

    public List<TasksArea> getAllTasksByAreaId(Long areasId) {
        return tasksAreaRepository.findByAreasId(areasId);
    }

    public Optional<TasksArea> getTaskById(Long areasId, Long tasksId) {
        return tasksAreaRepository.findById(tasksId)
                .filter(task -> task.getAreasId().equals(areasId));
    }

    public TasksArea saveTask(TasksArea task) {
        return tasksAreaRepository.save(task);
    }

    public void deleteTask(Long tasksId) {
        tasksAreaRepository.deleteById(tasksId);
    }
}