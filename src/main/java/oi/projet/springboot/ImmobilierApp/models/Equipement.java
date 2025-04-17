package oi.projet.springboot.ImmobilierApp.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
public class Equipement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEquipement;

    @Column(nullable = false)
    private String nomEquipement;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Installation> installations;


}
