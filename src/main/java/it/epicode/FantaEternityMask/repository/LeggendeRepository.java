package it.epicode.FantaEternityMask.repository;


import it.epicode.FantaEternityMask.entities.Leggende;
import it.epicode.FantaEternityMask.enums.RuoloLeggende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeggendeRepository extends JpaRepository<Leggende, Long> {
    @Query("SELECT l FROM Leggende l WHERE l.ruolo = :ruolo")
    List<Leggende> findByRuolo(RuoloLeggende ruolo);
}