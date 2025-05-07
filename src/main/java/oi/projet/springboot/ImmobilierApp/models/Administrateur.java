package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("Administrateur")
@Getter
@Setter
@AllArgsConstructor
public class Administrateur extends User implements Serializable {



    @ManyToMany
    @JoinTable(name = "Admin_Residence",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "idResidence")
    )
    private List<Residence> residences;


    public Administrateur() {

    }

    public Administrateur(String imageURL, String nom, String prenom, String adresse, String telephone1, String telephone2, String email, String username, String password, Role role) {
        super(imageURL, nom, prenom, adresse, telephone1, telephone2, email, username, password, role);
    }
}
