package oi.projet.springboot.ImmobilierApp.models;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Maintenance {

    public enum Statut{
        Demandé,
        EnCours,
        Terminé
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idMaintenance;
    private String nomMaintenance;
    private Date dateM;
    private String Description;
    private Statut statut;
    @ManyToOne
    @JoinColumn(name = "Locataire_ID")
    private Locataire locataire;
    @ManyToOne
    @JoinColumn(name = "technicien_ID")
    private TechnicienDeMaintenance technicienDeMaintenance;
    @ManyToOne
    @JoinColumn(name = "administrateur_ID")
    private Administrateur administrateur;
    @ManyToOne
    @JoinColumn(name = "gestionnaire_ID")
    private GestionnaireDeResidence gestionnaireDeResidence;
    @ManyToOne
    @JoinColumn(name = "residence_ID")
    private Residence residences;


    
    

    public Maintenance() {
    }

    public Maintenance(String nomMaintenance, Date dateM, String description) {
        this.nomMaintenance = nomMaintenance;
        this.dateM = dateM;
        Description = description;
        this.statut = Statut.Demandé;
    }
    
    public String getNomMaintenance() {
        return nomMaintenance;
    }
    public void setNomMaintenance(String nomMaintenance) {
        this.nomMaintenance = nomMaintenance;
    }
    public Date getDateM() {
        return dateM;
    }
    public void setDateM(Date dateM) {
        this.dateM = dateM;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public Statut getStatut() {
        return statut;
    }
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public long getIdMaintenance() {
        return idMaintenance;
    }

    public void setIdMaintenance(long idMaintenance) {
        this.idMaintenance = idMaintenance;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public TechnicienDeMaintenance getTechnicienDeMaintenance() {
        return technicienDeMaintenance;
    }

    public void setTechnicienDeMaintenance(TechnicienDeMaintenance technicienDeMaintenance) {
        this.technicienDeMaintenance = technicienDeMaintenance;
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

    public Residence getResidences() {
        return residences;
    }

    public void setResidences(Residence residences) {
        this.residences = residences;
    }


    

}
