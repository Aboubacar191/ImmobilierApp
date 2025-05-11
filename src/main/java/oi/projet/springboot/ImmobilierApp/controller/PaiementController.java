package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Mapper.PaiementMapper;
import oi.projet.springboot.ImmobilierApp.Services.PaiementService;
import oi.projet.springboot.ImmobilierApp.DTO.PaiementDTO;
import oi.projet.springboot.ImmobilierApp.Mapper.PaiementMapper;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.repository.LocataireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private LocataireRepository locataireRepository;

    @Autowired
    private PaiementMapper paiementMapper;

    @GetMapping
    public ResponseEntity<List<PaiementDTO>> getAllPaiements() {
        List<PaiementDTO> paiements = paiementService.getAllPaiements()
                .stream()
                .map(paiementMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paiements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaiementDTO> getPaiementById(@PathVariable Long id) {
        Optional<Paiement> paiement = paiementService.getPaiementById(id);
        return paiement.map(p -> ResponseEntity.ok(paiementMapper.toDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPaiement(@RequestBody PaiementDTO paiementDTO) {
        if (paiementDTO.getNomLocataire() == null || paiementDTO.getPrenomLocataire() == null) {
            return ResponseEntity.badRequest().body("Nom et prénom du locataire requis");
        }

        // Recherche du locataire par nom et prénom
        Optional<Locataire> locataireOpt = locataireRepository
                .findByNomAndPrenom(paiementDTO.getNomLocataire(), paiementDTO.getPrenomLocataire());

        if (locataireOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Locataire introuvable avec le nom et prénom fournis");
        }

        Paiement paiement = paiementMapper.toEntity(paiementDTO, locataireOpt.get());
        Paiement saved = paiementService.savePaiement(paiement);
        return ResponseEntity.ok(paiementMapper.toDTO(saved));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaiement(@PathVariable Long id, @RequestBody PaiementDTO paiementDTO) {
        if (paiementDTO.getNomLocataire() == null || paiementDTO.getPrenomLocataire() == null) {
            return ResponseEntity.badRequest().body("Nom et prénom du locataire requis");
        }

        Optional<Locataire> locataireOpt = locataireRepository
                .findByNomAndPrenom(paiementDTO.getNomLocataire(), paiementDTO.getPrenomLocataire());

        if (locataireOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Locataire introuvable avec le nom et prénom fournis");
        }

        Paiement paiement = paiementMapper.toEntity(paiementDTO, locataireOpt.get());
        paiement.setIdPaiement(id);  // Assurer la mise à jour
        Paiement updated = paiementService.savePaiement(paiement);
        return ResponseEntity.ok(paiementMapper.toDTO(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
