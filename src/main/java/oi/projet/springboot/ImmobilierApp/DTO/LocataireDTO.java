package oi.projet.springboot.ImmobilierApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocataireDTO {

    private long id;
    private String imageURL;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone1;
    private String telephone2;
    private String email;
    private String username;
    private String role;

    private int numeroDuLit;
    private String contrat;
    private String nomResidence;

    private List<PaiementDTO> paiementList;
}
