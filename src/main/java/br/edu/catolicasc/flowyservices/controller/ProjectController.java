package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.Project;
import br.edu.catolicasc.flowyservices.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            Project updatedProject = project.get();
            updatedProject.setName(projectDetails.getName());
            updatedProject.setProjectCheck(projectDetails.getProjectCheck());
            projectService.saveProject(updatedProject);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectService.getProjectById(id).isPresent()) {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/archives")
    public ResponseEntity<List<Project>> getArchivedProjects() {
        List<Project> archivedProjects = projectService.getArchivedProjects();
        return ResponseEntity.ok(archivedProjects);
    }

    @PostMapping("/{projectId}/checked")
    public ResponseEntity<Void> updateProjectCheck(@PathVariable Long projectId, @RequestParam Boolean checked) {
        projectService.updateProjectCheck(projectId, checked);
        return ResponseEntity.ok().build();
    }
}