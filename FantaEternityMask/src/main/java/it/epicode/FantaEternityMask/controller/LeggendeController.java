package it.epicode.FantaEternityMask.controller;

import it.epicode.FantaEternityMask.dto.LeggendeDTO;
import it.epicode.FantaEternityMask.service.LeggendeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leggende")
public class LeggendeController {

    @Autowired
    private LeggendeService leggendeService;

    @GetMapping
    public List<LeggendeDTO> getAllLeggende() {
        return leggendeService.getAllLeggende();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeggendeDTO> getLeggendeById(@PathVariable Long id) {
        Optional<LeggendeDTO> leggendeDTO = leggendeService.getLeggendeById(id);
        return leggendeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LeggendeDTO> createLeggende(@RequestBody LeggendeDTO leggendeDTO) {
        LeggendeDTO createdLeggende = leggendeService.createLeggende(leggendeDTO);
        return ResponseEntity.ok(createdLeggende);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeggendeDTO> updateLeggende(@PathVariable Long id, @RequestBody LeggendeDTO leggendeDTO) {
        Optional<LeggendeDTO> updatedLeggende = leggendeService.updateLeggende(id, leggendeDTO);
        return updatedLeggende.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeggende(@PathVariable Long id) {
        leggendeService.deleteLeggende(id);
        return ResponseEntity.noContent().build();
    }
}