package oi.projet.springboot.ImmobilierApp.Services;

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
        return locataireRepository.save(locataire);
    }

    public void deleteLocataire(Long id) {
        if (locataireRepository.existsById(id)) {
            locataireRepository.deleteById(id);
        } else {
            throw new RuntimeException("Locataire introuvable avec l'ID : " + id);
        }
    }
}
