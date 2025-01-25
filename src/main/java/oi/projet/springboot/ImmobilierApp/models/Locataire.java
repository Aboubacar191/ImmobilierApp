package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
@Entity
@DiscriminatorValue("Locataire")
public class Locataire extends Utilisateur {

   @ManyToOne 
   @JoinColumn(name = "residence_ID")
   private Residence residence;
   @OneToMany(mappedBy = "locataire") 
   private Set<Paiement> paiements;


   

   public Locataire() {
    super();
}




public Locataire(String image, String nom, String prenom, String adresse, String email, int contact,
   String nomUtilisateur, String motDePasse){
       super(image, nom, prenom, adresse, email, contact, nomUtilisateur, motDePasse);
       paiements = new HashSet<>();
      
   
   }




public Residence getResidence() {
    return residence;
}




public void setResidence(Residence residence) {
    this.residence = residence;
}




public Set<Paiement> getPaiements() {
    return paiements;
}




public void setPaiements(Set<Paiement> paiements) {
    this.paiements = paiements;
}



}
