package it.epicode.FantaEternityMask.dto;

import lombok.Data;
import it.epicode.FantaEternityMask.entities.Utente;
import it.epicode.FantaEternityMask.enums.Ruolo;

@Data
public class AuthDataDto {
    private int id;
    private String accessToken;
    private String username;

    private String email;

    private String nome;

    private String cognome;

    private String avatar;

    private Ruolo ruolo;
}
