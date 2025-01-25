package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import oi.projet.springboot.ImmobilierApp.models.Residence.Tn;
import oi.projet.springboot.ImmobilierApp.repository.ResidenceRepository;
import java.util.Optional;


@Service
public class ResidenceService {
    @Autowired
    private ResidenceRepository residenceRepository;

    

    public List<Residence> getResidences() {
        List<Residence> residences = new ArrayList<>();
        residenceRepository.findAll().forEach(residence -> {
            residences.add(residence);
        });
        return residences;
    }

    public Residence getUneResidence(long idResidence){
        return residenceRepository.findById(idResidence).orElse(null);
    }

    public void deleteResidence(long idResidence){
        residenceRepository.deleteById(idResidence);
    }

    public void addResidence(Residence residence){

        residenceRepository.save(residence);
    }

    public void updateResidence(Residence residence, long idResidence) {
    Optional<Residence> existingResidenceOptional = residenceRepository.findById(idResidence);
    
    if (existingResidenceOptional.isPresent()) {
        Residence existingResidence = existingResidenceOptional.get();
        // Mettez à jour les champs nécessaires de l'entité existante
        existingResidence.setNomResidence(residence.getNomResidence());
        existingResidence.setImageExterieur(residence.getImageExterieur());
        existingResidence.setImageInterieur1(residence.getImageInterieur1());
        existingResidence.setImageInterieur2(residence.getImageInterieur2());
        existingResidence.setAdresseResidence(residence.getAdresseResidence());
        existingResidence.setNbActuelLocataire(residence.getNbActuelLocataire());
        existingResidence.setNbMaxLocataire(residence.getNbMaxLocataire());
        existingResidence.setTypeLogement(residence.getTypeLogement());
        
        // Sauvegardez l'entité mise à jour
        residenceRepository.save(existingResidence);
    } else {
        // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
        throw new EntityNotFoundException("Residence with id " + idResidence + " not found.");
    }
}

}
