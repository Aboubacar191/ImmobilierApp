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

    @Column(nullable = false)
    private String contrat;

    @OneToMany(mappedBy = "locataire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paiement> paiementList;

    @ManyToOne
    private Residence residence;


    public Locataire() {

    }
}
