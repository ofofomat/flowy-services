package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.Project;
import br.edu.catolicasc.flowyservices.entity.TasksProject;
import br.edu.catolicasc.flowyservices.entity.TasksProjectDTO;
import br.edu.catolicasc.flowyservices.service.ProjectService;
import br.edu.catolicasc.flowyservices.service.TasksProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TasksProjectController {

    @Autowired
    private TasksProjectService tasksProjectService;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<TasksProject> getTasksByProjectId(@PathVariable Long projectId) {
        return tasksProjectService.getTasksByProjectId(projectId);
    }

    @GetMapping("/{tasksProjectId}")
    public ResponseEntity<TasksProject> getTaskById(@PathVariable Long projectId, @PathVariable Long tasksProjectId) {
        Optional<TasksProject> tasksProject = tasksProjectService.getTaskById(tasksProjectId);
        if (tasksProject.isPresent() && tasksProject.get().getProjectId().equals(projectId)) {
            return ResponseEntity.ok(tasksProject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasksProject> createTask(@PathVariable Long projectId, @RequestBody TasksProjectDTO tasksProjectDetails) {
        Optional<Project> project = projectService.getProjectById(projectId);
        if (project.isPresent()) {
            Project existingProject = project.get();
            if (!existingProject.getProjectCheck()) {
                TasksProject tasksProject = new TasksProject();
                tasksProject.setProjectId(projectId); // Set the projectId
                tasksProject.setTitle(tasksProjectDetails.getTitle());
                tasksProject.setDescription(tasksProjectDetails.getDescription());
                tasksProject.setDate(tasksProjectDetails.getDate());
                tasksProject.setPriority(tasksProjectDetails.getPriority());

                TasksProject savedTasksProject = tasksProjectService.saveTask(tasksProject);
                return ResponseEntity.ok(savedTasksProject);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{tasksProjectId}")
    public ResponseEntity<TasksProject> updateTask(@PathVariable Long projectId, @PathVariable Long tasksProjectId, @RequestBody TasksProjectDTO tasksProjectDetails) {
        Optional<TasksProject> existingTask = tasksProjectService.getTaskById(tasksProjectId);
        if (existingTask.isPresent() && existingTask.get().getProjectId().equals(projectId)) {
            TasksProject updatedTask = existingTask.get();
            updatedTask.setTitle(tasksProjectDetails.getTitle());
            updatedTask.setDescription(tasksProjectDetails.getDescription());
            updatedTask.setDate(tasksProjectDetails.getDate());
            updatedTask.setPriority(tasksProjectDetails.getPriority());
            tasksProjectService.saveTask(updatedTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tasksProjectId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long tasksProjectId) {
        Optional<TasksProject> tasksProject = tasksProjectService.getTaskById(tasksProjectId);
        if (tasksProject.isPresent() && tasksProject.get().getProjectId().equals(projectId)) {
            tasksProjectService.deleteTask(tasksProjectId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
