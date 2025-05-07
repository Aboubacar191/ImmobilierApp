package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.*;
import lombok.*;

import javax.management.ObjectName;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UTILISATEUR", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
public abstract class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(nullable = false)
    protected String imageURL;

    @Column(nullable = false)
    protected String nom;

    @Column(nullable = false)
    protected String prenom;

    @Column(nullable = false)
    protected String adresse;

    @Pattern(regexp = "\\d{9,}", message = "Numéro de téléphone invalide")
    @Column(nullable = false, unique = true)
    protected String telephone1;

    @Pattern(regexp = "\\d{9,}", message = "Numéro de téléphone invalide")
    protected String telephone2;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email invalide")
    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    public enum Role {
        Administrateur,
        Gestionnaire,
        Locataire;


    }

    @Enumerated(EnumType.STRING)
    protected Role role; // interne à l'application, non utilisé par Spring Security

    public User() {

    }

    public User(String imageURL, String nom, String prenom, String adresse, String telephone1, String telephone2, String email, String username, String password, Role role) {
        this.imageURL = imageURL;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
