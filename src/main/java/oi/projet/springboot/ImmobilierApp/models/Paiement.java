package oi.projet.springboot.ImmobilierApp.models;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Paiement implements Serializable {

    public enum method {
        Physique,
        OrangeMoney,
        MTnMoney,
        MoovMoney,
        Wave

    }

    public enum Statut {
        impayé,
        payé

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPaiement;

    @Temporal(TemporalType.DATE)
    private Date date;

    private float montant;

    @Enumerated(EnumType.STRING)
    private method methodeDepaiement;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    private Locataire locataire;

    public Paiement() {

    }


}
