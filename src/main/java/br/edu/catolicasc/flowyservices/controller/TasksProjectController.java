package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.Project;
import br.edu.catolicasc.flowyservices.entity.TasksProject;
import br.edu.catolicasc.flowyservices.entity.dto.TasksProjectDTO;
import br.edu.catolicasc.flowyservices.exception.ResourceNotFoundException;
import br.edu.catolicasc.flowyservices.service.ProjectService;
import br.edu.catolicasc.flowyservices.service.TasksProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TasksProjectController {

    private final TasksProjectService tasksProjectService;
    private final ProjectService projectService;

    public TasksProjectController(TasksProjectService tasksProjectService, ProjectService projectService) {
        this.tasksProjectService = tasksProjectService;
        this.projectService = projectService;
    }

    @GetMapping
    public List<TasksProject> getTasksByProjectId(@PathVariable Long projectId) {
        return tasksProjectService.getTasksByProjectId(projectId);
    }

    @GetMapping("/{tasksProjectId}")
    public ResponseEntity<TasksProject> getTaskById(@PathVariable Long projectId, @PathVariable Long tasksProjectId) {
        TasksProject tasksProject = getValidatedTask(projectId, tasksProjectId);
        return ResponseEntity.ok(tasksProject);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TasksProject> createTask(@PathVariable Long projectId, @RequestBody TasksProjectDTO tasksProjectDetails) {
        Project project = projectService.getProjectById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (Boolean.TRUE.equals(project.getProjectCheck())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        TasksProject tasksProject = new TasksProject();
        tasksProject.setProjectId(projectId);
        tasksProject.setTitle(tasksProjectDetails.getTitle());
        tasksProject.setDescription(tasksProjectDetails.getDescription());
        tasksProject.setDate(tasksProjectDetails.getDate());
        tasksProject.setPriority(tasksProjectDetails.getPriority());

        TasksProject savedTasksProject = tasksProjectService.saveTask(tasksProject);
        return ResponseEntity.ok(savedTasksProject);
    }

    @PutMapping("/{tasksProjectId}")
    @Transactional
    public ResponseEntity<TasksProject> updateTask(@PathVariable Long projectId, @PathVariable Long tasksProjectId, @RequestBody TasksProjectDTO tasksProjectDetails) {
        TasksProject tasksProject = getValidatedTask(projectId, tasksProjectId);

        tasksProject.setTitle(tasksProjectDetails.getTitle());
        tasksProject.setDescription(tasksProjectDetails.getDescription());
        tasksProject.setDate(tasksProjectDetails.getDate());
        tasksProject.setPriority(tasksProjectDetails.getPriority());

        tasksProjectService.saveTask(tasksProject);
        return ResponseEntity.ok(tasksProject);
    }

    @DeleteMapping("/{tasksProjectId}")
    @Transactional
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long tasksProjectId) {
        getValidatedTask(projectId, tasksProjectId);
        tasksProjectService.deleteTask(tasksProjectId);
        return ResponseEntity.noContent().build();
    }

    private TasksProject getValidatedTask(Long projectId, Long tasksProjectId) {
        return tasksProjectService.getTaskById(tasksProjectId)
                .filter(task -> task.getProjectId().equals(projectId))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }
}
