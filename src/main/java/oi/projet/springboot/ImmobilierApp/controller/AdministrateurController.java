package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.AdministrateurService;
import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.Services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrateurs")
@CrossOrigin(origins = "*")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        return ResponseEntity.ok(administrateurService.getAllAdministrateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        Optional<Administrateur> administrateur = administrateurService.getAdministrateurById(id);
        return administrateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        return ResponseEntity.ok(administrateurService.saveAdministrateur(administrateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Long id, @RequestBody Administrateur administrateur) {
        administrateur.setId(id);
        return ResponseEntity.ok(administrateurService.saveAdministrateur(administrateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.noContent().build();
    }
}
