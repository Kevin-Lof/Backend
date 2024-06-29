package it.epicode.FantaEternityMask.entities;

import it.epicode.FantaEternityMask.enums.Ruolo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


@Data
@Entity
public class Utente {

    @Id
    @GeneratedValue
    @NotNull
    private int id;

    private String username;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private String avatar;
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}


