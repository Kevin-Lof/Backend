package it.epicode.FantaEternityMask.controller;

import it.epicode.FantaEternityMask.dto.SquadraDTO;
import it.epicode.FantaEternityMask.entities.Squadra;
import it.epicode.FantaEternityMask.entities.Utente;
import it.epicode.FantaEternityMask.repository.SquadraRepository;
import it.epicode.FantaEternityMask.service.SquadraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    @GetMapping("/squadre")
    public Page<Squadra> getAllSquadre(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "15") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return squadraService.getAllSquadre(page, size, sortBy);
    }

    @GetMapping("/squadre/{id}")
    public ResponseEntity<SquadraDTO> getSquadraById(@PathVariable Long id) {
        Optional<SquadraDTO> squadraDTO = squadraService.findSquadraById(id);
        return squadraDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

 @PostMapping("/squadre")
public ResponseEntity<SquadraDTO> createSquadra(@Valid @RequestBody SquadraDTO squadraDTO) {
    SquadraDTO createdSquadra = squadraService.createSquadra(squadraDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdSquadra);
}
    //@PostMapping("/squadre")
    //public ResponseEntity<SquadraDTO> createSquadra(@RequestBody SquadraDTO squadraDTO) {
      //  SquadraDTO createdSquadra = squadraService.createSquadra(squadraDTO);
      //  return ResponseEntity.ok(createdSquadra);


//    @PutMapping("/squadre/{id}")
//    public ResponseEntity<SquadraDTO> updateSquadra(@PathVariable Long id, @RequestBody SquadraDTO squadraDTO) {
//        Optional<SquadraDTO> updatedSquadra = squadraService.updateSquadra(id, squadraDTO);
//        return updatedSquadra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/squadre/{id}")
//    public ResponseEntity<Void> deleteSquadra(@PathVariable Long id) {
//        squadraService.deleteSquadra(id);
//        return ResponseEntity.noContent().build();

@PutMapping("/squadre/{id}")
public ResponseEntity<SquadraDTO> updateSquadra(@PathVariable Long id, @RequestBody SquadraDTO squadraDTO) {
    Optional<SquadraDTO> updatedSquadra = squadraService.updateSquadra(id, squadraDTO);
    return updatedSquadra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

@DeleteMapping("/squadre/{id}")
public ResponseEntity<Void> deleteSquadra(@PathVariable Long id) {
    squadraService.deleteSquadra(id);
    return ResponseEntity.noContent().build();
}
}
