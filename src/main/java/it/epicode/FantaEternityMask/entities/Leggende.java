package it.epicode.FantaEternityMask.entities;

import it.epicode.FantaEternityMask.enums.RuoloLeggende;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Leggende {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private String nome;

    private String cognome;

    private int valore;

    @Enumerated(EnumType.STRING)
    private RuoloLeggende ruolo;

    private String avatar;

    @ManyToOne
    @JoinColumn(name = "squadra_id")
    private Squadra squadra;

}
