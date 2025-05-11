package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.MaintenanceService;
import oi.projet.springboot.ImmobilierApp.DTO.MaintenanceDTO;
import oi.projet.springboot.ImmobilierApp.Mapper.MaintenanceMapper;
import oi.projet.springboot.ImmobilierApp.models.Maintenance;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import oi.projet.springboot.ImmobilierApp.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/maintenances")
@CrossOrigin(origins = "*")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private ResidenceRepository residenceRepository;

    @Autowired
    private MaintenanceMapper maintenanceMapper;

    // GET all maintenances as DTOs
    @GetMapping
    public ResponseEntity<List<MaintenanceDTO>> getAllMaintenances() {
        List<MaintenanceDTO> dtos = maintenanceService.getAllMaintenances()
                .stream()
                .map(maintenanceMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET maintenance by ID as DTO
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        Optional<Maintenance> maintenance = maintenanceService.getMaintenanceById(id);
        return maintenance
                .map(m -> ResponseEntity.ok(maintenanceMapper.toDTO(m)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create maintenance
    @PostMapping
    public ResponseEntity<?> createMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
        Optional<Residence> residenceOpt = residenceRepository.findByNomResidence(maintenanceDTO.getNomResidence());
        if (residenceOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Résidence introuvable");
        }

        Maintenance maintenance = maintenanceMapper.toEntity(maintenanceDTO, residenceOpt.get());
        Maintenance saved = maintenanceService.saveMaintenance(maintenance);
        return ResponseEntity.ok(maintenanceMapper.toDTO(saved));
    }

    // PUT update maintenance
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceDTO maintenanceDTO) {
        Optional<Residence> residenceOpt = residenceRepository.findByNomResidence(maintenanceDTO.getNomResidence());
        if (residenceOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Résidence introuvable");
        }

        Maintenance maintenance = maintenanceMapper.toEntity(maintenanceDTO, residenceOpt.get());
        maintenance.setIdMaintenance(id);
        Maintenance updated = maintenanceService.saveMaintenance(maintenance);
        return ResponseEntity.ok(maintenanceMapper.toDTO(updated));
    }

    // DELETE maintenance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
