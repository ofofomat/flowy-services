package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.Area;
import br.edu.catolicasc.flowyservices.repository.AreaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Cacheable("areas")
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @Cacheable(value = "area", key = "#id")
    public Optional<Area> getAreaById(Long id) {
        return areaRepository.findById(id);
    }

    @Transactional
    public Area saveArea(Area area) {
        return areaRepository.save(area);
    }

    @Transactional
    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }
}