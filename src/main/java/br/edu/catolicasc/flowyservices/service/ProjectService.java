package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.Project;
import br.edu.catolicasc.flowyservices.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> getArchivedProjects() {
        return projectRepository.findByProjectCheckTrue();
    }

    public List<Project> getActiveProjects() {
        return projectRepository.findByProjectCheckFalse();
    }

    public void updateProjectCheck(Long projectId, Boolean checked) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        project.setProjectCheck(checked);
        projectRepository.save(project);
    }
}