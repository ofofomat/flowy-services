package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.TasksArea;
import br.edu.catolicasc.flowyservices.repository.TasksAreaRepository;
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

class TasksAreaServiceTest {

    @Mock
    private TasksAreaRepository tasksAreaRepository;

    @InjectMocks
    private TasksAreaService tasksAreaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasksByAreaId() {
        Long areaId = 1L;
        TasksArea task1 = new TasksArea();
        TasksArea task2 = new TasksArea();
        List<TasksArea> tasksList = Arrays.asList(task1, task2);

        when(tasksAreaRepository.findByAreasId(areaId)).thenReturn(tasksList);

        List<TasksArea> result = tasksAreaService.getTasksByAreaId(areaId);

        assertEquals(2, result.size());
        verify(tasksAreaRepository, times(1)).findByAreasId(areaId);
    }

    @Test
    void testGetTaskById() {
        Long taskId = 1L;
        TasksArea task = new TasksArea();

        when(tasksAreaRepository.findById(taskId)).thenReturn(Optional.of(task));

        Optional<TasksArea> result = tasksAreaService.getTaskById(taskId);

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
        verify(tasksAreaRepository, times(1)).findById(taskId);
    }

    @Test
    void testSaveTask() {
        TasksArea task = new TasksArea();

        when(tasksAreaRepository.save(task)).thenReturn(task);

        TasksArea result = tasksAreaService.saveTask(task);

        assertEquals(task, result);
        verify(tasksAreaRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;

        doNothing().when(tasksAreaRepository).deleteById(taskId);

        tasksAreaService.deleteTask(taskId);

        verify(tasksAreaRepository, times(1)).deleteById(taskId);
    }
}