package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.DTO.LocataireDTO;
import oi.projet.springboot.ImmobilierApp.Services.LocataireService;
import oi.projet.springboot.ImmobilierApp.Mapper.LocataireMapper;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locataires")
@CrossOrigin(origins = "*")
public class LocataireController {

    @Autowired
    private LocataireService locataireService;

    @Autowired
    private LocataireMapper locataireMapper;

    @GetMapping
    public ResponseEntity<List<LocataireDTO>> getAllLocataires() {
        List<LocataireDTO> dtos = locataireService.getAllLocataires().stream()
                .map(locataireMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocataireDTO> getLocataireById(@PathVariable Long id) {
        Optional<Locataire> locataireOpt = locataireService.getLocataireById(id);
        return locataireOpt.map(loc -> ResponseEntity.ok(locataireMapper.toDTO(loc)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LocataireDTO> createLocataire(@RequestBody LocataireDTO dto) {
        // Tu peux améliorer cette partie avec un service qui gère la résidence et paiements
        Locataire locataire = locataireMapper.toEntity(dto, null, null);
        Locataire saved = locataireService.saveLocataire(locataire);
        return ResponseEntity.ok(locataireMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocataireDTO> updateLocataire(@PathVariable Long id, @RequestBody LocataireDTO dto) {
        dto.setId(id);
        Locataire locataire = locataireMapper.toEntity(dto, null, null);
        Locataire updated = locataireService.saveLocataire(locataire);
        return ResponseEntity.ok(locataireMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocataire(@PathVariable Long id) {
        locataireService.deleteLocataire(id);
        return ResponseEntity.noContent().build();
    }
}
