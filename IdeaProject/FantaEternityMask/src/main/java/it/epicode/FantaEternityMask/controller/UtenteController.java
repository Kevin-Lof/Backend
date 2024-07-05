package it.epicode.FantaEternityMask.controller;

import it.epicode.FantaEternityMask.dto.UtenteDTO;
import it.epicode.FantaEternityMask.entities.Leggende;
import it.epicode.FantaEternityMask.entities.Utente;
import it.epicode.FantaEternityMask.exceptions.BadRequestException;
import it.epicode.FantaEternityMask.exceptions.NotFoundException;
import it.epicode.FantaEternityMask.service.LeggendeService;
import it.epicode.FantaEternityMask.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;




    @GetMapping("/utenti")
    public Page<Utente> getAllUtenti(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "15") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.getAllUtenti(page, size, sortBy);

    }



    @GetMapping("/utenti/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Utente getUserById(@PathVariable int id) {
        Optional<Utente> utenteOptional = utenteService.getUtenteById(id);

        if (utenteOptional.isPresent()) {
            return utenteOptional.get();
        } else {
            throw new NotFoundException("User with id=" + id + " not found");
        }
    }

    @PutMapping("/utenti/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Utente updateUtente(@PathVariable int id, @RequestBody @Validated UtenteDTO UtenteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return utenteService.updateUtente(id, UtenteDTO);
    }

    @DeleteMapping("/utenti/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUtente(@PathVariable int id) {
        return utenteService.deleteUtente(id);
    }


    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PatchMapping("/utenti/avatar")
    public String patchAvatarUtente(MultipartFile foto) throws IOException {
        return utenteService.patchAvatarUtente(foto);
    }


}
