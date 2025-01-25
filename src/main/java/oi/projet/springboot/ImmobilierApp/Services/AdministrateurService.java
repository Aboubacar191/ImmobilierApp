package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    

    @Autowired
    private AdministrateurRepository administrateurRepository;

    public List<Administrateur> getAdministrateurs() {
        List<Administrateur> administrateurs = new ArrayList<>();
        administrateurRepository.findAll().forEach(administrateurs::add);
        return administrateurs;
    }

    public Administrateur getUnAdministrateur(long id) {
        return administrateurRepository.findById(id).orElse(null);
    }

    public void deleteAdministrateur(long id) {
        administrateurRepository.deleteById(id);
    }

    public void addAdministrateur(Administrateur administrateur) {
        
        administrateurRepository.save(administrateur);
    }

    public void updateAdministrateur(Administrateur administrateur, long id) {
        Optional<Administrateur> existingAdministrateurOptional = administrateurRepository.findById(id);

        if (existingAdministrateurOptional.isPresent()) {
            Administrateur existingAdministrateur = existingAdministrateurOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingAdministrateur.setImage(administrateur.getImage());
            existingAdministrateur.setNom(administrateur.getNom());
            existingAdministrateur.setPrenom(administrateur.getPrenom());
            existingAdministrateur.setAdresse(administrateur.getAdresse());
            existingAdministrateur.setEmail(administrateur.getEmail());
            existingAdministrateur.setContact(administrateur.getContact());
            existingAdministrateur.setNomUtilisateur(administrateur.getNomUtilisateur());
            existingAdministrateur.setMotDePasse(administrateur.getMotDePasse());

            // Sauvegardez l'entité mise à jour
            administrateurRepository.save(existingAdministrateur);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Administrateur with id " + id + " not found.");
        }
    }
}
