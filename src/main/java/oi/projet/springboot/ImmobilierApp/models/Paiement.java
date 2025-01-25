package oi.projet.springboot.ImmobilierApp.models;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Paiement {

    public enum method {
        Physique,
        OrangeMoney,
        MTnMoney,
        MoovMoney,
        Wave

    }

    public enum Statut {
        en_Cours,
        Validé

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPaiement;
    private Date date;
    private float montant;
    private method methodeDePaiement;
    private int numeroDuVirement;
    private Statut statut;
    @ManyToOne
    @JoinColumn(name = "Locataire_ID")
    private Locataire locataire;
    @ManyToOne
    @JoinColumn(name = "administrateur_ID")
    private Administrateur administrateur;
    @ManyToOne
    @JoinColumn(name = "gestionnaire_ID")
    private GestionnaireDeResidence gestionnaireDeResidence;


    

    public Paiement() {
    }


    public Paiement(Date date, float montant, method methodeDePaiement, int numeroDuVirement) {
        this.date = date;
        this.montant = montant;
        this.methodeDePaiement = methodeDePaiement;
        this.numeroDuVirement = numeroDuVirement;
        this.statut = Statut.en_Cours;
    }

    
    public long getIdPaiement() {
        return idPaiement;
    }


    public void setIdPaiement(long idPaiement) {
        this.idPaiement = idPaiement;
    }


    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }
    public method getMethodeDePaiement() {
        return methodeDePaiement;
    }
    public void setMethodeDePaiement(method methodeDePaiement) {
        this.methodeDePaiement = methodeDePaiement;
    }
    public int getNumeroDuVirement() {
        return numeroDuVirement;
    }
    public void setNumeroDuVirement(int numeroDuVirement) {
        this.numeroDuVirement = numeroDuVirement;
    }
    public Statut getStatut() {
        return statut;
    }
    public void setStatut(Statut statut) {
        this.statut = statut;
    }


    public Locataire getLocataire() {
        return locataire;
    }


    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }


    public Administrateur getAdministrateur() {
        return administrateur;
    }


    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }


    public GestionnaireDeResidence getGestionnaireDeResidence() {
        return gestionnaireDeResidence;
    }


    public void setGestionnaireDeResidence(GestionnaireDeResidence gestionnaireDeResidence) {
        this.gestionnaireDeResidence = gestionnaireDeResidence;
    }




}
