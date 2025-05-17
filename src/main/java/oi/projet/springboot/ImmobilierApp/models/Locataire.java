package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue(value = "LOCATAIRE")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Locataire extends User implements Serializable {


    private int numeroDuLit;


    private String contrat;

    @OneToMany(mappedBy = "locataire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paiement> paiementList;

    @ManyToOne
    private Residence residence;


    public Locataire() {


    }

    public Locataire(String imageURL, String nom, String prenom, String adresse, String telephone1, String telephone2, String email, String username, String password, Role role, int numeroDuLit, String contrat) {
        super(imageURL, nom, prenom, adresse, telephone1, telephone2, email, username, password, role);
        this.numeroDuLit = numeroDuLit;
        this.contrat = contrat;
    }


}
