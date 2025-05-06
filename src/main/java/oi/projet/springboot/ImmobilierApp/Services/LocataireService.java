package oi.projet.springboot.ImmobilierApp.Services;

import oi.projet.springboot.ImmobilierApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.repository.LocataireRepository;

@Service
public class LocataireService {

    @Autowired
    private LocataireRepository locataireRepository;

    public List<Locataire> getAllLocataires() {
        return locataireRepository.findAll();
    }

    public Optional<Locataire> getLocataireById(Long id) {
        return locataireRepository.findById(id);
    }

    public Locataire saveLocataire(Locataire locataire) {
        locataire.setRole(User.Role.Locataire); // sécurité : toujours forcer le rôle
        return locataireRepository.save(locataire);
    }

    public Locataire updateLocataire(Long id, Locataire locataireDetails) {
        return locataireRepository.findById(id).map(locataire -> {
            locataire.setImageURL(locataireDetails.getImageURL());
            locataire.setNom(locataireDetails.getNom());
            locataire.setPrenom(locataireDetails.getPrenom());
            locataire.setAdresse(locataireDetails.getAdresse());
            locataire.setTelephone1(locataireDetails.getTelephone1());
            locataire.setTelephone2(locataireDetails.getTelephone2());
            locataire.setEmail(locataireDetails.getEmail());
            locataire.setNumeroDuLit(locataireDetails.getNumeroDuLit());
            locataire.setContrat(locataireDetails.getContrat());
            locataire.setResidence(locataireDetails.getResidence());

            return locataireRepository.save(locataire);
        }).orElseThrow(() -> new RuntimeException("Locataire introuvable avec l'ID : " + id));
    }

    public void deleteLocataire(Long id) {
        if (locataireRepository.existsById(id)) {
            locataireRepository.deleteById(id);
        } else {
            throw new RuntimeException("Locataire introuvable avec l'ID : " + id);
        }
    }
}

