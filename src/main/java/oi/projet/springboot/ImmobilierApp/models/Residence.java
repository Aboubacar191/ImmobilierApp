package oi.projet.springboot.ImmobilierApp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@ToString
@Data
@Getter
@Setter
@AllArgsConstructor
public class Residence implements Serializable {

   public enum Tn {
        T1,
        T2,
        T3,
        T4,
        T5
   }
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idResidence;

    @Column(length = 60 , nullable = false)
    private String nomResidence;

    @Column(nullable = true)
    private String imageExterieur;

    @Column(nullable = true)
    private String imageInterieur1;

    @Column(nullable = true)
    private String imageInterieur2;

    @Column(nullable = false)
    private String adresseResidence;

    @Column(nullable = false)
    private long nbActuelLocataire;

    @Column(nullable = false)
    private long nbMaxLocataire;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tn TypeLogement;

    @OneToMany(mappedBy = "residence",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    @JsonIgnore
    private List<Locataire> locataireList;

    @OneToMany(mappedBy = "residence",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Maintenance> maintenanceList;


    @OneToMany(mappedBy = "residence",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Installation> installations;

    @ManyToMany
    @JoinTable(name = "Admin_Residence", joinColumns = @JoinColumn(name = "idResidence"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @JsonIgnore
    private List<Administrateur> administrateurs;

 public Residence() {

 }

 public Residence(String nomResidence, String imageExterieur, String imageInterieur1, String imageInterieur2, String adresseResidence, long nbActuelLocataire, long nbMaxLocataire, Tn typeLogement) {
  this.nomResidence = nomResidence;
  this.imageExterieur = imageExterieur;
  this.imageInterieur1 = imageInterieur1;
  this.imageInterieur2 = imageInterieur2;
  this.adresseResidence = adresseResidence;
  this.nbActuelLocataire = nbActuelLocataire;
  this.nbMaxLocataire = nbMaxLocataire;
  TypeLogement = typeLogement;
 }
}
