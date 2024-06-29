package it.epicode.FantaEternityMask.controller;

import it.epicode.FantaEternityMask.dto.SquadraDTO;
import it.epicode.FantaEternityMask.service.SquadraService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/squadre")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    @GetMapping
    public List<SquadraDTO> getAllSquadre() {
        return squadraService.findAllSquadre();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SquadraDTO> getSquadraById(@PathVariable int Id) {
        Optional<SquadraDTO> squadraDTO = squadraService.findSquadraById(getSquadraById(Id));
        return squadraDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SquadraDTO> createSquadra(@RequestBody SquadraDTO squadraDTO) {
        SquadraDTO createdSquadra = squadraService.createSquadra(squadraDTO);
        return ResponseEntity.ok(createdSquadra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SquadraDTO> updateSquadra(@PathVariable Long id, @RequestBody SquadraDTO squadraDTO) {
        Optional<SquadraDTO> updatedSquadra = squadraService.updateSquadra(id, squadraDTO);
        return updatedSquadra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(@PathVariable Long id) {
        squadraService.deleteSquadra(id);
        return ResponseEntity.noContent().build();
    }
}
