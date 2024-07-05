package it.epicode.FantaEternityMask.controller;

import it.epicode.FantaEternityMask.dto.LeggendeDTO;
import it.epicode.FantaEternityMask.entities.Leggende;
import it.epicode.FantaEternityMask.service.LeggendeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class LeggendeController {

    @Autowired
    private LeggendeService leggendeService;

    //@GetMapping
    //public List<LeggendeDTO> getAllLeggende() {
        //return leggendeService.getAllLeggende();
    //}
    @GetMapping("/leggende")
    public Page<Leggende> getAllLeggende(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "15") int size,                                  @RequestParam(defaultValue = "id") String sortBy) {
       return leggendeService.getAllLeggende(page, size, sortBy);
    }

    @GetMapping("/leggende/{id}")
    public ResponseEntity<LeggendeDTO> getLeggendeById(@PathVariable Long id) {
        Optional<LeggendeDTO> leggendeDTO = leggendeService.getLeggendeById(id);
        return leggendeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//    @GetMapping("/leggende/{ruolo}")
//    public List<Leggende> getLeggendeByRuolo(@RequestParam String ruolo) {
//        return leggendeService.getLeggendeByRuolo(ruolo);
//    }
    @PostMapping
    public ResponseEntity<LeggendeDTO> createLeggende(@RequestBody LeggendeDTO leggendeDTO) {
        LeggendeDTO createdLeggende = leggendeService.createLeggende(leggendeDTO);
        return ResponseEntity.ok(createdLeggende);
    }

    @PutMapping("/leggende/{id}")
    public ResponseEntity<LeggendeDTO> updateLeggende(@PathVariable Long id, @RequestBody LeggendeDTO leggendeDTO) {
        Optional<LeggendeDTO> updatedLeggende = leggendeService.updateLeggende(id, leggendeDTO);
        return updatedLeggende.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/leggende/{id}")
    public ResponseEntity<Void> deleteLeggende(@PathVariable Long id) {
        leggendeService.deleteLeggende(id);
        return ResponseEntity.noContent().build();
    }
}