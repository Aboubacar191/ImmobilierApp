package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.GestionnaireDeResidence;
import oi.projet.springboot.ImmobilierApp.repository.GestionnaireRepository;

@Service
public class GestionnaireService {

    @Autowired
    private GestionnaireRepository gestionnaireDeResidenceRepository;

    public List<GestionnaireDeResidence> getGestionnaires() {
        List<GestionnaireDeResidence> gestionnaires = new ArrayList<>();
        gestionnaireDeResidenceRepository.findAll().forEach(gestionnaires::add);
        return gestionnaires;
    }

    public GestionnaireDeResidence getUnGestionnaire(long id) {
        return gestionnaireDeResidenceRepository.findById(id).orElse(null);
    }

    public void deleteGestionnaire(long id) {
        gestionnaireDeResidenceRepository.deleteById(id);
    }

    public void addGestionnaire(GestionnaireDeResidence gestionnaire) {
        gestionnaireDeResidenceRepository.save(gestionnaire);
    }

    public void updateGestionnaire(GestionnaireDeResidence gestionnaire, long id) {
        Optional<GestionnaireDeResidence> existingGestionnaireOptional = gestionnaireDeResidenceRepository.findById(id);

        if (existingGestionnaireOptional.isPresent()) {
            GestionnaireDeResidence existingGestionnaire = existingGestionnaireOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingGestionnaire.setImage(gestionnaire.getImage());
            existingGestionnaire.setNom(gestionnaire.getNom());
            existingGestionnaire.setPrenom(gestionnaire.getPrenom());
            existingGestionnaire.setAdresse(gestionnaire.getAdresse());
            existingGestionnaire.setEmail(gestionnaire.getEmail());
            existingGestionnaire.setContact(gestionnaire.getContact());
            existingGestionnaire.setNomUtilisateur(gestionnaire.getNomUtilisateur());
            existingGestionnaire.setMotDePasse(gestionnaire.getMotDePasse());

            // Sauvegardez l'entité mise à jour
            gestionnaireDeResidenceRepository.save(existingGestionnaire);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Gestionnaire with id " + id + " not found.");
        }
    }
}
