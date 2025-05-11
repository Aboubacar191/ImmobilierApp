package oi.projet.springboot.ImmobilierApp.Mapper;

import oi.projet.springboot.ImmobilierApp.DTO.InstallationDTO;
import oi.projet.springboot.ImmobilierApp.models.Equipement;
import oi.projet.springboot.ImmobilierApp.models.Installation;
import oi.projet.springboot.ImmobilierApp.models.InstallationKey;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.stereotype.Component;

@Component
public class InstallationMapper {

    public InstallationDTO toDTO(Installation installation) {
        if (installation == null) return null;

        return new InstallationDTO(
                installation.getResidence() != null ? installation.getResidence().getNomResidence() : null,
                installation.getEquipment() != null ? installation.getEquipment().getIdEquipement() : null,
                installation.getEquipment() != null ? installation.getEquipment().getNomEquipement() : null,
                installation.getEquipment() != null ? installation.getEquipment().getDescription() : null,
                installation.getQuantite()
        );
    }

    public Installation toEntity(InstallationDTO dto, Residence residence, Equipement equipement) {
        if (dto == null || residence == null || equipement == null) return null;

        InstallationKey id = new InstallationKey(residence.getIdResidence(), equipement.getIdEquipement());

        Installation installation = new Installation();
        installation.setInstallationKey(id);
        installation.setResidence(residence);
        installation.setEquipment(equipement);
        installation.setQuantite(dto.getQuantite());

        return installation;
    }
}
