package oi.projet.springboot.ImmobilierApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstallationDTO {

    private String Nomresidence;
    private Long equipementId;
    private String nomEquipement;
    private String description;
    private int quantite;

    public String getNomResidence() {
        return Nomresidence;
    }
}

