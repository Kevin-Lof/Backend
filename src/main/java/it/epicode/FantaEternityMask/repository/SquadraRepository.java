package it.epicode.FantaEternityMask.repository;

import it.epicode.FantaEternityMask.entities.Squadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SquadraRepository extends JpaRepository<Squadra, Long> {

    @Query("SELECT s FROM Squadra s WHERE s.nome = ?1")
    List<Squadra> findByNome(String nome);

    @Query("SELECT s FROM Squadra s WHERE s.utente.id = ?1")
    List<Squadra> findByUtenteId(Long utenteId);


}
