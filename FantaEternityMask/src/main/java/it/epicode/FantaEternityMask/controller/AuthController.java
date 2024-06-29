package it.epicode.FantaEternityMask.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import it.epicode.FantaEternityMask.dto.AuthDataDto;
import it.epicode.FantaEternityMask.dto.UtenteLoginDTO;
import it.epicode.FantaEternityMask.dto.UtenteDTO;
import it.epicode.FantaEternityMask.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.epicode.FantaEternityMask.service.AuthService;
import it.epicode.FantaEternityMask.service.UtenteService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UtenteDTO utenteDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return utenteService.saveUtente(utenteDTO);
    }

    @PostMapping("/auth/login")
    public AuthDataDto login(@RequestBody @Validated UtenteLoginDTO utenteLoginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return authService.authenticateUtenteAndCreateToken(utenteLoginDTO);
    }
}
