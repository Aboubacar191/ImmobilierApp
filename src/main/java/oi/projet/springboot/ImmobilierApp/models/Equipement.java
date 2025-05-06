package oi.projet.springboot.ImmobilierApp.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Data
@Getter
@Setter
@AllArgsConstructor
public class Equipement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEquipement;

    @Column(nullable = false)
    private String nomEquipement;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Installation> installations;

    public Equipement() {

    }


}
