package oi.projet.springboot.ImmobilierApp.models;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
@Entity
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEquipement;
    private String nomEquipement;
    private String description;
    private int quantité;
    private String nomEmplacement;
    
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.EAGER)
    private List<Residence> residences;

    
    
    

    public Equipement() {
    }





    public Equipement(String nomEquipement, String description, int quantité, String nomEmplacement) {
        this.nomEquipement = nomEquipement;
        this.description = description;
        this.quantité = quantité;
        this.nomEmplacement = nomEmplacement;
        residences= new ArrayList<>();
    }





    public long getIdEquipement() {
        return idEquipement;
    }





    public void setIdEquipement(long idEquipement) {
        this.idEquipement = idEquipement;
    }





    public String getNomEquipement() {
        return nomEquipement;
    }





    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }





    public String getDescription() {
        return description;
    }





    public void setDescription(String description) {
        this.description = description;
    }





    public int getQuantité() {
        return quantité;
    }





    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }





    public String getNomEmplacement() {
        return nomEmplacement;
    }





    public void setNomEmplacement(String nomEmplacement) {
        this.nomEmplacement = nomEmplacement;
    }





    public List<Residence> getResidences() {
        return residences;
    }





    public void setResidences(List<Residence> residences) {
        this.residences = residences;
    }

    

    

   




}
