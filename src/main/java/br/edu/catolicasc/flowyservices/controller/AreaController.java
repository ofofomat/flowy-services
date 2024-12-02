package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.Area;
import br.edu.catolicasc.flowyservices.exception.ResourceNotFoundException;
import br.edu.catolicasc.flowyservices.service.AreaService;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    public List<Area> getAllAreas() {
        return areaService.getAllAreas();
    }

    @GetMapping("/{areasId}")
    public ResponseEntity<Area> getAreaById(@PathVariable Long areasId) {
        Area area = areaService.getAreaById(areasId)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found with id " + areasId));
        return ResponseEntity.ok(area);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Area> createArea(@RequestBody Area area) {
        Area createdArea = areaService.saveArea(area);
        return ResponseEntity.status(201).body(createdArea);
    }

    @PutMapping("/{areasId}")
    @Transactional
    public ResponseEntity<Area> updateArea(@PathVariable Long areasId, @RequestBody Area areaDetails) {
        Area area = areaService.getAreaById(areasId)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found with id " + areasId));
        area.setName(areaDetails.getName());
        Area updatedArea = areaService.saveArea(area);
        return ResponseEntity.ok(updatedArea);
    }

    @DeleteMapping("/{areasId}")
    @Transactional
    public ResponseEntity<Void> deleteArea(@PathVariable Long areasId) {
        Area area = areaService.getAreaById(areasId)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found with id " + areasId));
        areaService.deleteArea(areasId);
        return ResponseEntity.noContent().build();
    }
}
