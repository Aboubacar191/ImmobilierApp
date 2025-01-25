package oi.projet.springboot.ImmobilierApp.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Gestionnaire")
public class GestionnaireDeResidence extends Utilisateur {
    @OneToMany(mappedBy = "gestionnaireDeResidence")
    private List<Maintenance> maintenances;

    public GestionnaireDeResidence(String image, String nom, String prenom, String adresse, String email, int contact,
    String nomUtilisateur, String motDePasse){
        super(image, nom, prenom, adresse, email, contact, nomUtilisateur, motDePasse);
        maintenances = new ArrayList<>();
    
    }
    

    public GestionnaireDeResidence() {
        super();
    }


    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }



}
