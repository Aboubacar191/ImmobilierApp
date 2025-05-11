package oi.projet.springboot.ImmobilierApp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oi.projet.springboot.ImmobilierApp.models.Installation;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidenceDTO {

    private Long idResidence;
    private String nomResidence;
    private String imageExterieur;
    private String imageInterieur1;
    private String imageInterieur2;
    private String adresseResidence;
    private long nbActuelLocataire;
    private long nbMaxLocataire;
    private String typeLogement;

    private List<MaintenanceDTO> maintenanceList;
    private List<InstallationDTO> installations;



}

