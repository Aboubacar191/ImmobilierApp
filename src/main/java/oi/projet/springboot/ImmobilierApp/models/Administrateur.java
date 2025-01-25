package oi.projet.springboot.ImmobilierApp.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Admin")
public class Administrateur extends Utilisateur {
    @OneToMany(mappedBy = "administrateur")
    private Set<Maintenance> maintenances;

    

    public Administrateur() {
        super();
    }

    public Administrateur(String image, String nom, String prenom, String adresse, String email, int contact,
    String nomUtilisateur, String motDePasse){
        super(image, nom, prenom, adresse, email, contact, nomUtilisateur, motDePasse);
        maintenances = new HashSet<>();
    
    }

    public Set<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(Set<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    



}
