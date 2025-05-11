package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.DTO.InstallationDTO;
import oi.projet.springboot.ImmobilierApp.Mapper.InstallationMapper;
import oi.projet.springboot.ImmobilierApp.Services.EquipementService;
import oi.projet.springboot.ImmobilierApp.Services.InstallationService;
import oi.projet.springboot.ImmobilierApp.Services.ResidenceService;
import oi.projet.springboot.ImmobilierApp.models.Installation;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import oi.projet.springboot.ImmobilierApp.models.Equipement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/installations")
@CrossOrigin(origins = "*")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @Autowired
    private ResidenceService residenceService;

    @Autowired
    private EquipementService equipementService;

    @Autowired
    private InstallationMapper installationMapper;

    @GetMapping
    public ResponseEntity<List<InstallationDTO>> getAllInstallations() {
        List<InstallationDTO> dtos = installationService.getAllInstallations()
                .stream()
                .map(installationMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<InstallationDTO> createInstallation(@RequestBody InstallationDTO dto) {
        Optional<Residence> residence = residenceService.findByNomResidence(dto.getNomResidence());
        Optional<Equipement> equipement = equipementService.getEquipementById(dto.getEquipementId());

        if (residence.isEmpty() || equipement.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Installation saved = installationService.saveInstallation(
                installationMapper.toEntity(dto, residence.get(), equipement.get())
        );
        return ResponseEntity.ok(installationMapper.toDTO(saved));
    }

    @DeleteMapping("/{residenceName}/{equipementId}")
    public ResponseEntity<Void> deleteInstallation(@PathVariable String residenceName, @PathVariable Long equipementId) {
        installationService.deleteInstallationByCompositeKey(residenceName, equipementId);
        return ResponseEntity.noContent().build();
    }
}
