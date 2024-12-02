package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.*;
import br.edu.catolicasc.flowyservices.entity.dto.TasksAreaDTO;
import br.edu.catolicasc.flowyservices.service.AreaService;
import br.edu.catolicasc.flowyservices.service.TasksAreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas/{areasId}/tasks")
public class TasksAreaController {

    private final TasksAreaService tasksAreaService;
    private final AreaService areaService;

    public TasksAreaController(TasksAreaService tasksAreaService, AreaService areaService) {
        this.tasksAreaService = tasksAreaService;
        this.areaService = areaService;
    }

    @GetMapping
    public List<TasksArea> getAllTasksByAreaId(@PathVariable Long areasId) {
        return tasksAreaService.getTasksByAreaId(areasId);
    }

    @GetMapping("/{tasksAreaId}")
    public ResponseEntity<TasksArea> getTaskById(@PathVariable Long areasId, @PathVariable Long tasksAreaId) {
        return tasksAreaService.getTaskById(tasksAreaId)
                .filter(task -> task.getAreasId().equals(areasId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TasksArea> createTask(@PathVariable Long areasId, @RequestBody TasksAreaDTO task) {
        return areaService.getAreaById(areasId)
                .map(area -> {
                    TasksArea tasksArea = new TasksArea();
                    tasksArea.setAreasId(areasId);
                    tasksArea.setTitle(task.getTitle());
                    tasksArea.setDescription(task.getDescription());
                    tasksArea.setRecurrence(task.getRecurrence());
                    tasksArea.setPriority(task.getPriority());

                    TasksArea savedTasksArea = tasksAreaService.saveTask(tasksArea);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedTasksArea);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{tasksAreaId}")
    public ResponseEntity<TasksArea> updateTask(@PathVariable Long areasId, @PathVariable Long tasksAreaId, @RequestBody TasksAreaDTO taskDetails) {
        return tasksAreaService.getTaskById(tasksAreaId)
                .filter(task -> task.getAreasId().equals(areasId))
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setRecurrence(taskDetails.getRecurrence());
                    task.setPriority(taskDetails.getPriority());
                    tasksAreaService.saveTask(task);
                    return ResponseEntity.ok(task);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{tasksAreaId}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long areasId, @PathVariable Long tasksAreaId) {
        return tasksAreaService.getTaskById(tasksAreaId)
                .filter(task -> task.getAreasId().equals(areasId))
                .map(task -> {
                    tasksAreaService.deleteTask(tasksAreaId);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
