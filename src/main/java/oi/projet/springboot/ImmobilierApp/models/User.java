package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UTILISATEUR", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
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

    @Min(value = 100000000, message = "Numéro de téléphone invalide")
    @Column(nullable = false, unique = true)
    protected String telephone1;

    @Column
    protected String telephone2;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", message = "Email invalide")
    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    public User() {

    }

    public enum Role {
        Administrateur,
        Gestionnaire,
        Locataire
    }

    @Enumerated(EnumType.STRING)
    protected Role Role; // interne à l'application, non utilisé par Spring Security


}
