package oi.projet.springboot.ImmobilierApp.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
public class Locataire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String adresse;

    @Min(value = 100000000, message = "Numéro de téléphone invalide")
    @Column(nullable = false, unique = true)
    private String telephone1;

    @Column(nullable = true)
    private String telephone2;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", message = "Email invalide")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int NumeroDuLit;

    @Column(nullable = false)
    private String Contrat;

    @OneToMany(mappedBy = "locataire",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Paiement> paiementList;

    @OneToMany(mappedBy = "locataires",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Maintenance> maintenanceList;

    @ManyToOne
    private Residence residence;

    @OneToOne
    @JoinColumn(name = "User_Id")
    private CompteUser compteuser;

}


