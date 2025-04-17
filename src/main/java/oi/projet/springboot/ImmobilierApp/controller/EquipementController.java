package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.models.Equipement;
import oi.projet.springboot.ImmobilierApp.Services.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipements")
@CrossOrigin(origins = "*")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    @GetMapping
    public ResponseEntity<List<Equipement>> getAllEquipements() {
        return ResponseEntity.ok(equipementService.getAllEquipements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        return equipementService.getEquipementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipement> createEquipement(@RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.saveEquipement(equipement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        equipement.setIdEquipement(id);
        return ResponseEntity.ok(equipementService.saveEquipement(equipement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
