package it.epicode.FantaEternityMask.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
@Data
@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String avatar;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
    private List<Leggende> leggende;


}

