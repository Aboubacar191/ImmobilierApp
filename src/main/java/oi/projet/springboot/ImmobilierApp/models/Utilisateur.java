package oi.projet.springboot.ImmobilierApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public  class Utilisateur {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = true)
    private String image;
    @Column(nullable = false , length = 60)
    private String nom;
    @Column(nullable = false , length = 120)
    private String prenom;
    @Column(nullable = true)
    private String adresse;
    @Column(nullable = true)
    private String email;
    @Column(nullable = false)
    private int Contact;
    @Column(nullable = false, length = 60)
    private String nomUtilisateur;
    @Column(nullable = false, length = 60)
    private String motDePasse;

    


    

    public Utilisateur() {
        
    }
    public Utilisateur(String image, String nom, String prenom, String adresse, String email, int contact,
            String nomUtilisateur, String motDePasse) {
          
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        Contact = contact;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }
    public long getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getEmail() {
        return email;
    }
    public int getContact() {
        return Contact;
    }
    public String getNomUtilisateur() {
        return this.nomUtilisateur;
    }
    public String getMotDePasse() {
        return this.motDePasse;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setContact(int contact) {
        Contact = contact;
    }
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    

}
