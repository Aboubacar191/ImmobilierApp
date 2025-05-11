package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.DTO.ResidenceDTO;
import oi.projet.springboot.ImmobilierApp.Mapper.ResidenceMapper;
import oi.projet.springboot.ImmobilierApp.Services.ResidenceService;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/residences")
@CrossOrigin(origins = "*")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @Autowired
    private ResidenceMapper residenceMapper;

    @GetMapping
    public ResponseEntity<List<ResidenceDTO>> getAllResidences() {
        List<ResidenceDTO> dtos = residenceService.getAllResidences()
                .stream()
                .map(residenceMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidenceDTO> getResidenceById(@PathVariable Long id) {
        Optional<Residence> residenceOpt = residenceService.getResidenceById(id);
        return residenceOpt
                .map(residence -> ResponseEntity.ok(residenceMapper.toDTO(residence)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResidenceDTO> createResidence(@RequestBody ResidenceDTO dto) {
        Residence residence = residenceMapper.toEntity(dto); //
        Residence saved = residenceService.saveResidence(residence);
        return ResponseEntity.ok(residenceMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidenceDTO> updateResidence(@PathVariable Long id, @RequestBody ResidenceDTO dto) {
        dto.setIdResidence(id); // ✅ On garde l'ID correct
        Residence residence = residenceMapper.toEntity(dto); // ✅ Mapping DTO → Entity
        Residence saved = residenceService.saveResidence(residence);
        return ResponseEntity.ok(residenceMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResidence(@PathVariable Long id) {
        residenceService.deleteResidence(id);
        return ResponseEntity.noContent().build();
    }
}
