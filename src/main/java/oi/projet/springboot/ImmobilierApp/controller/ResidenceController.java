package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.ResidenceService;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import oi.projet.springboot.ImmobilierApp.Services.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/residences")
@CrossOrigin(origins = "*")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @GetMapping
    public ResponseEntity<List<Residence>> getAllResidences() {
        return ResponseEntity.ok(residenceService.getAllResidences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Residence> getResidenceById(@PathVariable Long id) {
        Optional<Residence> residence = residenceService.getResidenceById(id);
        return residence.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Residence> createResidence(@RequestBody Residence residence) {
        return ResponseEntity.ok(residenceService.saveResidence(residence));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Residence> updateResidence(@PathVariable Long id, @RequestBody Residence residence) {
        residence.setIdResidence(id);
        return ResponseEntity.ok(residenceService.saveResidence(residence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResidence(@PathVariable Long id) {
        residenceService.deleteResidence(id);
        return ResponseEntity.noContent().build();
    }
}
