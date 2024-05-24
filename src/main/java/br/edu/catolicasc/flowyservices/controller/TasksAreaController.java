package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.*;
import br.edu.catolicasc.flowyservices.service.AreaService;
import br.edu.catolicasc.flowyservices.service.TasksAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/areas/{areasId}/tasks")
public class TasksAreaController {

    @Autowired
    private TasksAreaService tasksAreaService;

    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<TasksArea> getAllTasksByAreaId(@PathVariable Long areasId) {
        return tasksAreaService.getAllTasksByAreaId(areasId);
    }

    @GetMapping("/{tasksAreaId}")
    public ResponseEntity<TasksArea> getTaskById(@PathVariable Long areasId, @PathVariable Long tasksAreaId) {
        Optional<TasksArea> taskArea = tasksAreaService.getTaskById(tasksAreaId);
        if (taskArea.isPresent() && taskArea.get().getAreasId().equals(areasId)) {
            return ResponseEntity.ok(taskArea.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@PathVariable Long areasId, @RequestBody TasksAreaDTO task) {
        Optional<Area> area = areaService.getAreaById(areasId);
        if (area.isPresent()) {
            TasksArea tasksArea = new TasksArea();
            tasksArea.setAreasId(areasId); // Set the projectId
            tasksArea.setTitle(task.getTitle());
            tasksArea.setDescription(task.getDescription());
            tasksArea.setRecurrence(task.getRecurrence());
            tasksArea.setPriority(task.getPriority());

            TasksArea savedTasksArea = tasksAreaService.saveTask(tasksArea);
            return ResponseEntity.ok(savedTasksArea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{tasksAreaId}")
    public ResponseEntity<TasksArea> updateTask(@PathVariable Long areasId, @PathVariable Long tasksAreaId, @RequestBody TasksAreaDTO taskDetails) {
        Optional<TasksArea> task = tasksAreaService.getTaskById(tasksAreaId);
        if (task.isPresent() && task.get().getAreasId().equals(areasId)) {
            TasksArea updatedTask = task.get();
            updatedTask.setTitle(taskDetails.getTitle());
            updatedTask.setDescription(taskDetails.getDescription());
            updatedTask.setRecurrence(taskDetails.getRecurrence());
            updatedTask.setPriority(taskDetails.getPriority());
            tasksAreaService.saveTask(updatedTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tasksAreaId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long areasId, @PathVariable Long tasksAreaId) {
        Optional<TasksArea> tasksArea = tasksAreaService.getTaskById(tasksAreaId);
        if (tasksArea.isPresent() && tasksArea.get().getAreasId().equals(areasId)) {
            tasksAreaService.deleteTask(tasksAreaId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}