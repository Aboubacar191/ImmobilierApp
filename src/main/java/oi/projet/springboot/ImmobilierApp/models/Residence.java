package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Residence {

   public enum Tn {
        T1,
        T2,
        T3,
        T4,
        T5
    }
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long idResidence;
    @Column(length = 60 , nullable = false)
    private String nomResidence;
    @Column(nullable = true)
    private String imageExterieur;
    @Column(nullable = true)
    private String imageInterieur1;
    @Column(nullable = true)
    private String imageInterieur2;
    @Column(nullable = true)
    private String adresseResidence;
    @Column(nullable = true)
    private long nbActuelLocataire;
    @Column(nullable = false)
    private long nbMaxLocataire;
    @Column(nullable = false)
    private Tn TypeLogement;
    @OneToMany(mappedBy = "residence" )
    private List<Locataire> Locataires;

    @OneToMany(mappedBy = "residences")
    private List<Maintenance> maintenances;

   @ManyToMany(fetch = FetchType.EAGER) 
   private List<Equipement> equipements;

    public Residence() {
        super();
    }


    public Residence(String nomResidence, String imageExterieur, String imageInterieur1,
            String imageInterieur2, String adresseResidence, long nbActuelLocataire, long nbMaxLocataire,
            Tn typeLogement) {
     
        this.nomResidence = nomResidence;
        this.imageExterieur = imageExterieur;
        this.imageInterieur1 = imageInterieur1;
        this.imageInterieur2 = imageInterieur2;
        this.adresseResidence = adresseResidence;
        this.nbActuelLocataire = nbActuelLocataire;
        this.nbMaxLocataire = nbMaxLocataire;
        TypeLogement = typeLogement;
        Locataires = new ArrayList<>();
        maintenances = new ArrayList<>();
        equipements = new ArrayList<>();


    }
    public long getIdResidence() {
        return idResidence;
    }
    public String getNomResidence() {
        return nomResidence;
    }
    public String getImageExterieur() {
        return imageExterieur;
    }
    public String getImageInterieur1() {
        return imageInterieur1;
    }
    public String getImageInterieur2() {
        return imageInterieur2;
    }
    public String getAdresseResidence() {
        return adresseResidence;
    }
    public long getNbActuelLocataire() {
        return nbActuelLocataire;
    }
    public long getNbMaxLocataire() {
        return nbMaxLocataire;
    }
    public Tn getTypeLogement() {
        return TypeLogement;
    }
    public void setIdResidence(long idResidence) {
        this.idResidence = idResidence;
    }
    public void setNomResidence(String nomResidence) {
        this.nomResidence = nomResidence;
    }
    public void setImageExterieur(String imageExterieur) {
        this.imageExterieur = imageExterieur;
    }
    public void setImageInterieur1(String imageInterieur1) {
        this.imageInterieur1 = imageInterieur1;
    }
    public void setImageInterieur2(String imageInterieur2) {
        this.imageInterieur2 = imageInterieur2;
    }
    public void setAdresseResidence(String adresseResidence) {
        this.adresseResidence = adresseResidence;
    }
    public void setNbActuelLocataire(long nbActuelLocataire) {
        this.nbActuelLocataire = nbActuelLocataire;
    }
    public void setNbMaxLocataire(long nbMaxLocataire) {
        this.nbMaxLocataire = nbMaxLocataire;
    }
    public void setTypeLogement(Tn typeLogement) {
        TypeLogement = typeLogement;
    }

    


}
