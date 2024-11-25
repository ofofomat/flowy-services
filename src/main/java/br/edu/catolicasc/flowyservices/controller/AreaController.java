package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.Area;
import br.edu.catolicasc.flowyservices.service.AreaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Area> area = areaService.getAreaById(areasId);
        if (area.isPresent()) {
            return ResponseEntity.ok(area.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Area createArea(@RequestBody Area area) {
        return areaService.saveArea(area);
    }

    @PutMapping("/{areasId}")
    public ResponseEntity<Area> updateArea(@PathVariable Long areasId, @RequestBody Area areaDetails) {
        Optional<Area> area = areaService.getAreaById(areasId);
        if (area.isPresent()) {
            Area updatedArea = area.get();
            updatedArea.setName(areaDetails.getName());
            areaService.saveArea(updatedArea);
            return ResponseEntity.ok(updatedArea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{areasId}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long areasId) {
        Optional<Area> area = areaService.getAreaById(areasId);
        if (area.isPresent()) {
            areaService.deleteArea(areasId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}