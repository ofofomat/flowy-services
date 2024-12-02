package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.Project;
import br.edu.catolicasc.flowyservices.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() {
        Project project1 = new Project();
        Project project2 = new Project();
        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        List<Project> projects = projectService.getAllProjects();

        assertEquals(2, projects.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        Project project = new Project();
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> foundProject = projectService.getProjectById(1L);

        assertTrue(foundProject.isPresent());
        assertEquals(project, foundProject.get());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveProject() {
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        Project savedProject = projectService.saveProject(project);

        assertEquals(project, savedProject);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testDeleteProject() {
        doNothing().when(projectRepository).deleteById(1L);

        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetArchivedProjects() {
        Project project1 = new Project();
        Project project2 = new Project();
        when(projectRepository.findByProjectCheck(true)).thenReturn(Arrays.asList(project1, project2));

        List<Project> projects = projectService.getArchivedProjects();

        assertEquals(2, projects.size());
        verify(projectRepository, times(1)).findByProjectCheck(true);
    }

    @Test
    void testGetActiveProjects() {
        Project project1 = new Project();
        Project project2 = new Project();
        when(projectRepository.findByProjectCheck(false)).thenReturn(Arrays.asList(project1, project2));

        List<Project> projects = projectService.getActiveProjects();

        assertEquals(2, projects.size());
        verify(projectRepository, times(1)).findByProjectCheck(false);
    }

    @Test
    void testUpdateProjectCheck() {
        Project project = new Project();
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(project)).thenReturn(project);

        projectService.updateProjectCheck(1L, true);

        assertTrue(project.getProjectCheck());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(project);
    }
}