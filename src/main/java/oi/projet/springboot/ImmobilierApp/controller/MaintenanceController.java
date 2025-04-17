package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.MaintenanceService;
import oi.projet.springboot.ImmobilierApp.models.Maintenance;
import oi.projet.springboot.ImmobilierApp.Services.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenances")
@CrossOrigin(origins = "*")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Optional<Maintenance> maintenance = maintenanceService.getMaintenanceById(id);
        return maintenance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.saveMaintenance(maintenance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        maintenance.setIdMaintenance(id);
        return ResponseEntity.ok(maintenanceService.saveMaintenance(maintenance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
