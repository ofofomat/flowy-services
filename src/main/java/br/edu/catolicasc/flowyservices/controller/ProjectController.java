package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.Project;
import br.edu.catolicasc.flowyservices.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;
import br.edu.catolicasc.flowyservices.exception.ResourceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> activeProjects = projectService.getActiveProjects();
        return ResponseEntity.ok(activeProjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.saveProject(project);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        project.setName(projectDetails.getName());
        project.setProjectCheck(projectDetails.getProjectCheck());
        Project updatedProject = projectService.saveProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.getProjectById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/archives")
    public ResponseEntity<List<Project>> getArchivedProjects() {
        List<Project> archivedProjects = projectService.getArchivedProjects();
        return ResponseEntity.ok(archivedProjects);
    }

    @PostMapping("/{projectId}/checked")
    @Transactional
    public ResponseEntity<Void> updateProjectCheck(@PathVariable Long projectId, @RequestParam Boolean checked) {
        projectService.updateProjectCheck(projectId, checked);
        return ResponseEntity.ok().build();
    }
}
