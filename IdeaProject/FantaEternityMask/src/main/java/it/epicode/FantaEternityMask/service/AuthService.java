package it.epicode.FantaEternityMask.service;


import it.epicode.FantaEternityMask.security.JwtTool;
import it.epicode.FantaEternityMask.dto.AuthDataDto;
import it.epicode.FantaEternityMask.dto.UtenteLoginDTO;
import it.epicode.FantaEternityMask.entities.Utente;
import it.epicode.FantaEternityMask.exceptions.NotFoundException;
import it.epicode.FantaEternityMask.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.epicode.FantaEternityMask.security.JwtTool;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthDataDto authenticateUtenteAndCreateToken(UtenteLoginDTO utenteLoginDto) {
        Optional<Utente> utenteOptional = utenteService.getUtenteByEmail(utenteLoginDto.getEmail());

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            if (passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())) {
                 //jwtTool.createToken(utente);
                 AuthDataDto authDataDto = new AuthDataDto();
                  authDataDto.setAccessToken(jwtTool.createToken(utente));
                 authDataDto.setRuolo(utente.getRuolo());
                  authDataDto.setNome(utente.getNome());
                    authDataDto.setCognome(utente.getCognome());
                    authDataDto.setEmail(utente.getEmail());
                    authDataDto.setUsername(utente.getUsername());
                    authDataDto.setId(utente.getId());
                  authDataDto.setAvatar(utente.getAvatar());
                    return authDataDto;

            } else {
                throw new UnauthorizedException("Errore nel login, riloggarsi");
            }

        } else {
            throw new NotFoundException("Utente con email " + utenteLoginDto.getEmail() + "non trovato ");
        }
    }
}