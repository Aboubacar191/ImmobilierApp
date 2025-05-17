package oi.projet.springboot.ImmobilierApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDetailsDTO {
    private Long idPaiement;
    private Date date;
    private float montant;
    private String methodeDepaiement;
    private String statut;

    private String nomLocataire;
    private String prenomLocataire;
    private String adresseLocataire;
    private String telephoneLocataire;
    private String email;
    private String nomResidence;
    private  int numeroDeLit;
    private String typeLogement;

}
