package it.epicode.FantaEternityMask.repository;

import it.epicode.FantaEternityMask.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {



    Optional<Utente> findByUsername(String username);

    static Optional<Utente> findById(Long id) {
        return null;
    }

    @Query("SELECT u FROM Utente u WHERE u.email LIKE %?1%")
    List<Utente> findByEmailLike(String email);

    @Query("SELECT COUNT(u) FROM Utente u")
    long countAllUsers();

    Optional<Utente> findByEmail(String email);
}
