package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.TasksArea;
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

    @GetMapping
    public List<TasksArea> getAllTasksByAreaId(@PathVariable Long areasId) {
        return tasksAreaService.getAllTasksByAreaId(areasId);
    }

    @GetMapping("/{tasksId}")
    public ResponseEntity<TasksArea> getTaskById(@PathVariable Long areasId, @PathVariable Long tasksId) {
        Optional<TasksArea> task = tasksAreaService.getTaskById(areasId, tasksId);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public TasksArea createTask(@PathVariable Long areasId, @RequestBody TasksArea task) {
        task.setAreasId(areasId);
        return tasksAreaService.saveTask(task);
    }

    @PutMapping("/{tasksId}")
    public ResponseEntity<TasksArea> updateTask(@PathVariable Long areasId, @PathVariable Long tasksId, @RequestBody TasksArea taskDetails) {
        Optional<TasksArea> task = tasksAreaService.getTaskById(areasId, tasksId);
        if (task.isPresent()) {
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

    @DeleteMapping("/{tasksId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long areasId, @PathVariable Long tasksId) {
        Optional<TasksArea> task = tasksAreaService.getTaskById(areasId, tasksId);
        if (task.isPresent()) {
            tasksAreaService.deleteTask(tasksId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}