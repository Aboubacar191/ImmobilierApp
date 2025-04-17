package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.Services.LocataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locataires")
@CrossOrigin(origins = "*")
public class LocataireController {

    @Autowired
    private LocataireService locataireService;

    @GetMapping
    public ResponseEntity<List<Locataire>> getAllLocataires() {
        return ResponseEntity.ok(locataireService.getAllLocataires());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locataire> getLocataireById(@PathVariable Long id) {
        return locataireService.getLocataireById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Locataire> createLocataire(@RequestBody Locataire locataire) {
        return ResponseEntity.ok(locataireService.saveLocataire(locataire));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locataire> updateLocataire(@PathVariable Long id, @RequestBody Locataire locataire) {
        locataire.setId(id);
        return ResponseEntity.ok(locataireService.saveLocataire(locataire));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocataire(@PathVariable Long id) {
        locataireService.deleteLocataire(id);
        return ResponseEntity.noContent().build();
    }
}
