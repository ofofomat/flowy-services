package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.Area;
import br.edu.catolicasc.flowyservices.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<Area> getAreaById(Long id) {
        return areaRepository.findById(id);
    }

    public Area saveArea(Area area) {
        return areaRepository.save(area);
    }

    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }
}