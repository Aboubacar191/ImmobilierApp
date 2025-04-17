package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.PaiementService;
import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.Services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        Optional<Paiement> paiement = paiementService.getPaiementById(id);
        return paiement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementService.savePaiement(paiement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        paiement.setIdPaiement(id);
        return ResponseEntity.ok(paiementService.savePaiement(paiement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
