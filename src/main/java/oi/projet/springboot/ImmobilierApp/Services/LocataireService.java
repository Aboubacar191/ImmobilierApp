package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.repository.LocataireRepository;

@Service
public class LocataireService {

    @Autowired
    private LocataireRepository locataireRepository;

    public List<Locataire> getLocataires() {
        List<Locataire> locataires = new ArrayList<>();
        locataireRepository.findAll().forEach(locataires::add);
        return locataires;
    }

    public Locataire getUnLocataire(long id) {
        return locataireRepository.findById(id).orElse(null);
    }

    public void deleteLocataire(long id) {
        locataireRepository.deleteById(id);
    }

    public void addLocataire(Locataire locataire) {
        locataireRepository.save(locataire);
    }

    public void updateLocataire(Locataire locataire, long id) {
        Optional<Locataire> existingLocataireOptional = locataireRepository.findById(id);

        if (existingLocataireOptional.isPresent()) {
            Locataire existingLocataire = existingLocataireOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingLocataire.setImage(locataire.getImage());
            existingLocataire.setNom(locataire.getNom());
            existingLocataire.setPrenom(locataire.getPrenom());
            existingLocataire.setAdresse(locataire.getAdresse());
            existingLocataire.setEmail(locataire.getEmail());
            existingLocataire.setContact(locataire.getContact());
            existingLocataire.setNomUtilisateur(locataire.getNomUtilisateur());
            existingLocataire.setMotDePasse(locataire.getMotDePasse());

            // Sauvegardez l'entité mise à jour
            locataireRepository.save(existingLocataire);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Locataire with id " + id + " not found.");
        }
    }
}
