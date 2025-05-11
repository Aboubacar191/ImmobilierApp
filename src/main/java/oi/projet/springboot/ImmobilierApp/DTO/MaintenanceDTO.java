package oi.projet.springboot.ImmobilierApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDTO {

    private long idMaintenance;
    private String nomMaintenance;
    private Date dateM;
    private String description;
    private String statut; // ex: "Demandé", "EnCours", "Terminé"
    private String nomResidence; // Clé étrangère : uniquement le nom
}
