package oi.projet.springboot.ImmobilierApp.Mapper;

import oi.projet.springboot.ImmobilierApp.DTO.MaintenanceDTO;
import oi.projet.springboot.ImmobilierApp.models.Maintenance;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceMapper {

    // Convertir une entité Maintenance vers un DTO
    public MaintenanceDTO toDTO(Maintenance maintenance) {
        if (maintenance == null) return null;

        return new MaintenanceDTO(
                maintenance.getIdMaintenance(),
                maintenance.getNomMaintenance(),
                maintenance.getDateM(),
                maintenance.getDescription(),
                maintenance.getStatut().name(),
                maintenance.getResidence() != null ? maintenance.getResidence().getNomResidence() : null
        );
    }

    // Convertir un DTO vers une entité Maintenance
    public Maintenance toEntity(MaintenanceDTO dto, Residence residence) {
        if (dto == null) return null;

        Maintenance maintenance = new Maintenance();
        maintenance.setIdMaintenance(dto.getIdMaintenance());
        maintenance.setNomMaintenance(dto.getNomMaintenance());
        maintenance.setDateM(dto.getDateM());
        maintenance.setDescription(dto.getDescription());
        maintenance.setStatut(Maintenance.Statut.valueOf(dto.getStatut()));
        maintenance.setResidence(residence);

        return maintenance;
    }
}

