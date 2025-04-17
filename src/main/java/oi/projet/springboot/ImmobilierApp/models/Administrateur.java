package oi.projet.springboot.ImmobilierApp.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter

public class Administrateur implements Serializable {

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

    @OneToMany(mappedBy = "administrateur",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Paiement> paiements;

    @ManyToMany
    @JoinTable(name = "Admin_Residence", joinColumns = @JoinColumn(name = "AdminId"),
            inverseJoinColumns = @JoinColumn(name = "ResidId")
    )
    private List<Residence> residences;

    @OneToMany(mappedBy = "administrateur",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<CompteUser> compteUsers;


}
