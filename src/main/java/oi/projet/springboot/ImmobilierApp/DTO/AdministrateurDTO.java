package oi.projet.springboot.ImmobilierApp.DTO;


import lombok.Data;
import oi.projet.springboot.ImmobilierApp.models.User;

@Data
public class AdministrateurDTO {

    private long id;
    private String imageURL;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone1;
    private String telephone2;
    private String email;
    private String username;
    private User.Role role; // ou juste String si tu veux exposer sous forme de texte
}
