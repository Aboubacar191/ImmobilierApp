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
@ToString
@Data
@Getter
@Setter
@AllArgsConstructor
public class Maintenance implements Serializable {

    public enum Statut{
        Demandé,
        EnCours,
        Terminé
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idMaintenance;

    @Column(nullable = false)
    private String nomMaintenance;

    @Column(nullable = false)
    private Date dateM;

    @Column(nullable = false)
    private String Description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    private Residence residence;

    public Maintenance() {

    }


}
