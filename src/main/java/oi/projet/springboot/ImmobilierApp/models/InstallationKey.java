package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class InstallationKey implements Serializable {

    private static final long serialVersionUID = 12346780987543L;

    @Column(name = "Equipenemt_id")
    private  long EquipementId;

    @Column(name = "Residence_id")
    private  long ResidenceId;
}
