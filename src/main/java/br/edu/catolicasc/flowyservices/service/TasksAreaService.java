package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.TasksArea;
import br.edu.catolicasc.flowyservices.repository.TasksAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TasksAreaService {

    @Autowired
    private TasksAreaRepository tasksAreaRepository;

    public List<TasksArea> getTasksByAreaId(Long areaId) {
        return tasksAreaRepository.findByAreasId(areaId);
    }

    public Optional<TasksArea> getTaskById(Long taskId) {
        return tasksAreaRepository.findById(taskId);
    }

    @Transactional
    public TasksArea saveTask(TasksArea task) {
        return tasksAreaRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        tasksAreaRepository.deleteById(taskId);
    }
}