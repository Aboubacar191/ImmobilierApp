package oi.projet.springboot.ImmobilierApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Installation implements Serializable {

    private static final long serialVersionUID = 12346780987543L;

    @EmbeddedId
    private InstallationKey id;

    @ManyToOne()
    @JoinColumn(name = "residence_id")
    @MapsId("ResidenceId")
    @JsonBackReference
    private Residence residence;

    @ManyToOne()
    @MapsId("EquipementId")
    @JoinColumn(name = "equipement_id")

    private Equipement equipment;


    private int Quantite;

    public InstallationKey setInstallationKey(InstallationKey id){
        return this.id = id;
    }

    public Installation() {

    }


}
