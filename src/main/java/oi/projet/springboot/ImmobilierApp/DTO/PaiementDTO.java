package oi.projet.springboot.ImmobilierApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {

    private Long idPaiement;
    private Date date;
    private float montant;
    private String methodeDepaiement;
    private String statut;

    private String nomLocataire;
    private String prenomLocataire;  // ou username si tu préfères
}

