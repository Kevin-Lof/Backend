package it.epicode.FantaEternityMask.service;

import it.epicode.FantaEternityMask.dto.LeggendeDTO;
import it.epicode.FantaEternityMask.entities.Leggende;
import it.epicode.FantaEternityMask.repository.LeggendeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeggendeService {

    @Autowired
    private LeggendeRepository leggendeRepository;

    //public List<LeggendeDTO> getAllLeggende() {
    //    return leggendeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    //}

    public Page<Leggende> getAllLeggende (int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return leggendeRepository.findAll(pageable);

    }

    public Optional<LeggendeDTO> getLeggendeById(Long id) {
        return leggendeRepository.findById(id).map(this::convertToDTO);
    }

    public LeggendeDTO createLeggende(LeggendeDTO leggendeDTO) {
        Leggende leggende = new Leggende();
        leggende.setNome(leggendeDTO.getNome());
        leggende.setNome(leggendeDTO.getCognome());
        leggende.setRuolo(leggendeDTO.getRuolo());
        leggende.setValore(leggendeDTO.getValore());
        leggende.setNome(leggendeDTO.getAvatar());

        Leggende savedLeggende = leggendeRepository.save(leggende);
        return convertToDTO(savedLeggende);
    }

    public Optional<LeggendeDTO> updateLeggende(Long id, LeggendeDTO leggendeDTO) {
        return leggendeRepository.findById(id).map(leggende -> {
            leggende.setNome(leggendeDTO.getNome());
            leggende.setCognome(leggendeDTO.getCognome());
            leggende.setRuolo(leggendeDTO.getRuolo());
            leggende.setValore(leggendeDTO.getValore());
            leggende.setAvatar(leggendeDTO.getAvatar());
            Leggende updatedLeggende = leggendeRepository.save(leggende);
            return convertToDTO(updatedLeggende);
        });
    }

    public void deleteLeggende(Long id) {
        leggendeRepository.deleteById(id);
    }

    private LeggendeDTO convertToDTO(Leggende leggende) {
        return new LeggendeDTO(leggende.getId(), leggende.getNome(), leggende.getCognome(), leggende.getRuolo(), leggende.getValore(), leggende.getAvatar());
    }
}
