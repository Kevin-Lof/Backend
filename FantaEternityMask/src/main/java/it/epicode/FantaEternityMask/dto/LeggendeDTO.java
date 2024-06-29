package it.epicode.FantaEternityMask.dto;

import it.epicode.FantaEternityMask.enums.RuoloLeggende;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LeggendeDTO {
    private String nome;

    private String cognome;

    private int valore;

    @Enumerated(EnumType.STRING)
    private RuoloLeggende ruolo;

    private String avatar;

    public LeggendeDTO(Long id, String nome, String cognome, RuoloLeggende ruolo, int valore, String avatar) {
    }

    public String getNome() {
        return "";
    }

    public String getCognome() {
        return "";
    }

    public RuoloLeggende getRuolo() {
        return null;
    }

    public int getValore() {
        return 0;
    }

    public String getAvatar() {
        return "";
    }
}
