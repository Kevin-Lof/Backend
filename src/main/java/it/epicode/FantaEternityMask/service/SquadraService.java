package it.epicode.FantaEternityMask.service;

import it.epicode.FantaEternityMask.dto.SquadraDTO;
import it.epicode.FantaEternityMask.entities.Squadra;
import it.epicode.FantaEternityMask.entities.Utente;
import it.epicode.FantaEternityMask.repository.SquadraRepository;
import it.epicode.FantaEternityMask.repository.UtenteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SquadraService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private SquadraRepository squadraRepository;

    public List<SquadraDTO> findAllSquadre() {
        List<Squadra> squadre = squadraRepository.findAll();
        return squadre.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SquadraDTO> findSquadraById(ResponseEntity<SquadraDTO> squadraById) {
        int Id = 0;
        Optional<Squadra> squadraOptional = squadraRepository.findById ((long) Id);
        return squadraOptional.map(this::convertToDTO);
    }

    // Altri metodi del service

    private SquadraDTO convertToDTO(Squadra squadra) {
        SquadraDTO squadraDTO = new SquadraDTO();
        BeanUtils.copyProperties(squadra, squadraDTO);
        return squadraDTO;
    }


    public SquadraDTO createSquadra(SquadraDTO squadraDTO) {
        Optional<Utente> utenteOpt = UtenteRepository.findById(squadraDTO.getUtenteId());
        if (!utenteOpt.isPresent()) {
            throw new RuntimeException("Utente non trovato");
        }
        Squadra squadra = new Squadra();
        squadra.setNome(squadraDTO.getNome());
        squadra.setUtente(utenteOpt.get());

        Squadra savedSquadra = squadraRepository.save(squadra);
        return convertToDTO(savedSquadra);
    }

    public Optional<SquadraDTO> updateSquadra(Long id, SquadraDTO squadraDTO) {
        return squadraRepository.findById(id).map(squadra -> {
            Optional<Utente> utenteOpt = UtenteRepository.findById(SquadraDTO.getUtenteId());
            if (!utenteOpt.isPresent()) {
                throw new RuntimeException("Utente non trovato");
            }

            squadra.setNome(squadraDTO.getNome());
            squadra.setUtente(utenteOpt.get());

            Squadra updatedSquadra = squadraRepository.save(squadra);
            return convertToDTO(updatedSquadra);
        });
    }

    public void deleteSquadra(Long id) {
        squadraRepository.deleteById(id);
    }
}