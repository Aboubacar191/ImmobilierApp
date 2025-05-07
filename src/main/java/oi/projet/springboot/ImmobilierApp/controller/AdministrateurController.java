package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.AdministrateurService;
import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.Services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/administrateurs")
@CrossOrigin(origins = "*")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    // Récupérer tous les administrateurs (administrateurs + gestionnaires)
    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        return ResponseEntity.ok(administrateurService.getAllAdministrateurs());
    }

    // Récupérer uniquement les administrateurs
    @GetMapping("/administrateurs")
    public ResponseEntity<List<Administrateur>> getOnlyAdministrateurs() {
        return ResponseEntity.ok(administrateurService.getOnlyAdministrateurs());
    }

    // Récupérer uniquement les gestionnaires
    @GetMapping("/gestionnaires")
    public ResponseEntity<List<Administrateur>> getGestionnaires() {
        return ResponseEntity.ok(administrateurService.getGestionnaires());
    }

    // Récupérer un administrateur/gestionnaire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        return administrateurService.getAdministrateurById(id)
                .map(ResponseEntity::ok)
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

