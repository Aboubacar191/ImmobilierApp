package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.DTO.ResidenceNomDTO;
import oi.projet.springboot.ImmobilierApp.Services.AdministrateurService;
import oi.projet.springboot.ImmobilierApp.DTO.AdministrateurDTO;
import oi.projet.springboot.ImmobilierApp.Mapper.AdministrateurMapper;
import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/administrateurs")
@CrossOrigin(origins = "*")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    // Récupérer tous les administrateurs (administrateurs + gestionnaires)
    @GetMapping
    public ResponseEntity<List<AdministrateurDTO>> getAllAdministrateurs() {
        List<Administrateur> admins = administrateurService.getAllAdministrateurs();
        List<AdministrateurDTO> dtos = admins.stream()
                .map(AdministrateurMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Récupérer uniquement les administrateurs
    @GetMapping("/administrateurs")
    public ResponseEntity<List<AdministrateurDTO>> getOnlyAdministrateurs() {
        List<Administrateur> admins = administrateurService.getOnlyAdministrateurs();
        List<AdministrateurDTO> dtos = admins.stream()
                .map(AdministrateurMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}/residences/noms")
    public ResponseEntity<List<ResidenceNomDTO>> getNomResidences(@PathVariable Long id) {
        try {
            List<ResidenceNomDTO> noms = administrateurService.getNomResidencesByAdminId(id);
            return ResponseEntity.ok(noms);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // Récupérer uniquement les gestionnaires
    @GetMapping("/gestionnaires")
    public ResponseEntity<List<AdministrateurDTO>> getGestionnaires() {
        List<Administrateur> gestionnaires = administrateurService.getGestionnaires();
        List<AdministrateurDTO> dtos = gestionnaires.stream()
                .map(AdministrateurMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Récupérer un administrateur/gestionnaire par ID
    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurDTO> getAdministrateurById(@PathVariable Long id) {
        Optional<Administrateur> adminOpt = administrateurService.getAdministrateurById(id);
        return adminOpt.map(admin -> ResponseEntity.ok(AdministrateurMapper.toDTO(admin)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter un administrateur ou gestionnaire
    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur saved = administrateurService.saveAdministrateur(administrateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Mettre à jour un administrateur ou gestionnaire
    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Long id, @RequestBody Administrateur administrateurDetails) {
        try {
            Administrateur updated = administrateurService.updateAdministrateur(id, administrateurDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un administrateur ou gestionnaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        try {
            administrateurService.deleteAdministrateur(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
