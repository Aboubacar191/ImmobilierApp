package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue(value = "ADMIN")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Administrateur extends User implements Serializable {



    @ManyToMany
    @JoinTable(name = "Admin_Residence",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "ResidId")
    )
    private List<Residence> residences;


    public Administrateur() {

    }
}
