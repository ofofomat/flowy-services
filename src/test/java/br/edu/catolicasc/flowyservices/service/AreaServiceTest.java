package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.Area;
import br.edu.catolicasc.flowyservices.repository.AreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AreaServiceTest {

    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private AreaService areaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAreas() {
        Area area1 = new Area();
        Area area2 = new Area();
        List<Area> areas = Arrays.asList(area1, area2);

        when(areaRepository.findAll()).thenReturn(areas);

        List<Area> result = areaService.getAllAreas();

        assertEquals(2, result.size());
        verify(areaRepository, times(1)).findAll();
    }

    @Test
    void testSaveArea() {
        Area area = new Area();

        when(areaRepository.save(area)).thenReturn(area);

        Area result = areaService.saveArea(area);

        assertNotNull(result);
        verify(areaRepository, times(1)).save(area);
    }

    @Test
    void testDeleteArea() {
        Long id = 1L;

        doNothing().when(areaRepository).deleteById(id);

        areaService.deleteArea(id);

        verify(areaRepository, times(1)).deleteById(id);
    }
}