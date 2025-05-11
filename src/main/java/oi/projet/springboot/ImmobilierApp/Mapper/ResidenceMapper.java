package oi.projet.springboot.ImmobilierApp.Mapper;

import oi.projet.springboot.ImmobilierApp.DTO.*;
import oi.projet.springboot.ImmobilierApp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResidenceMapper {

    private final MaintenanceMapper maintenanceMapper;
    private final InstallationMapper installationMapper;

    @Autowired
    public ResidenceMapper(MaintenanceMapper maintenanceMapper, InstallationMapper installationMapper) {
        this.maintenanceMapper = maintenanceMapper;
        this.installationMapper = installationMapper;
    }

    // Entity -> DTO
    public ResidenceDTO toDTO(Residence residence) {
        if (residence == null) return null;

        List<MaintenanceDTO> maintenanceDTOs = residence.getMaintenanceList() != null
                ? residence.getMaintenanceList().stream()
                .map(maintenanceMapper::toDTO)
                .collect(Collectors.toList())
                : null;

        List<InstallationDTO> installationDTOs = residence.getInstallations() != null
                ? residence.getInstallations().stream()
                .map(installationMapper::toDTO)
                .collect(Collectors.toList())
                : null;

        return new ResidenceDTO(
                residence.getIdResidence(),
                residence.getNomResidence(),
                residence.getImageExterieur(),
                residence.getImageInterieur1(),
                residence.getImageInterieur2(),
                residence.getAdresseResidence(),
                residence.getNbActuelLocataire(),
                residence.getNbMaxLocataire(),
                residence.getTypeLogement().name(),
                maintenanceDTOs,
                installationDTOs
        );
    }

    // DTO -> Entity (relations complexes à gérer à part si besoin)
    public static Residence toEntity(ResidenceDTO dto) {
        if (dto == null) return null;

        Residence residence = new Residence();
        residence.setIdResidence(dto.getIdResidence());
        residence.setNomResidence(dto.getNomResidence());
        residence.setImageExterieur(dto.getImageExterieur());
        residence.setImageInterieur1(dto.getImageInterieur1());
        residence.setImageInterieur2(dto.getImageInterieur2());
        residence.setAdresseResidence(dto.getAdresseResidence());
        residence.setNbActuelLocataire(dto.getNbActuelLocataire());
        residence.setNbMaxLocataire(dto.getNbMaxLocataire());
        residence.setTypeLogement(Residence.Tn.valueOf(dto.getTypeLogement()));



        return residence;
    }


}
