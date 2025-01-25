package oi.projet.springboot.ImmobilierApp.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
@DiscriminatorValue("Technicien")
public class TechnicienDeMaintenance extends Utilisateur {

    

    private String metier;
    @OneToMany(mappedBy = "technicienDeMaintenance")
    private Set<Maintenance> maintenances;

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    

    public TechnicienDeMaintenance() {
        super();
    }

    public TechnicienDeMaintenance(String image, String nom, String prenom, String adresse, String email, int contact,
            String nomUtilisateur, String motDePasse, String metier) {
        super(image, nom, prenom, adresse, email, contact, nomUtilisateur, motDePasse);
        this.metier = metier;
        maintenances = new HashSet<>();
    }

    


}
