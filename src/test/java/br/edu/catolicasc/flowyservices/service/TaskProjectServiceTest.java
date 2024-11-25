package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.TasksProject;
import br.edu.catolicasc.flowyservices.repository.TasksProjectRepository;
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

class TaskProjectServiceTest {

    @Mock
    TasksProjectRepository tasksProjectRepository;

    @InjectMocks
    TasksProjectService tasksProjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTasksByProjectId() {
        Long projectId = 1L;
        TasksProject task1 = new TasksProject();
        TasksProject task2 = new TasksProject();
        List<TasksProject> tasks = Arrays.asList(task1, task2);

        when(tasksProjectRepository.findByProjectId(projectId)).thenReturn(tasks);

        List<TasksProject> result = tasksProjectService.getTasksByProjectId(projectId);

        assertEquals(2, result.size());
        verify(tasksProjectRepository, times(1)).findByProjectId(projectId);
    }

    @Test
    void testGetTaskById() {
        Long taskId = 1L;
        TasksProject task = new TasksProject();

        when(tasksProjectRepository.findById(taskId)).thenReturn(Optional.of(task));

        Optional<TasksProject> result = tasksProjectService.getTaskById(taskId);

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
        verify(tasksProjectRepository, times(1)).findById(taskId);
    }

    @Test
    void testSaveTask() {
        TasksProject task = new TasksProject();

        when(tasksProjectRepository.save(task)).thenReturn(task);

        TasksProject result = tasksProjectService.saveTask(task);

        assertEquals(task, result);
        verify(tasksProjectRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;

        doNothing().when(tasksProjectRepository).deleteById(taskId);

        tasksProjectService.deleteTask(taskId);

        verify(tasksProjectRepository, times(1)).deleteById(taskId);
    }
}