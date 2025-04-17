package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    // Récupérer tous les administrateurs
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    // Récupérer un administrateur par son ID
    public Optional<Administrateur> getAdministrateurById(Long id) {
        return administrateurRepository.findById(id);
    }

    // Ajouter un nouvel administrateur
    public Administrateur saveAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    // Mettre à jour un administrateur existant
    public Administrateur updateAdministrateur(Long id, Administrateur administrateurDetails) {
        return administrateurRepository.findById(id).map(administrateur -> {
            administrateur.setImageURL(administrateurDetails.getImageURL());
            administrateur.setNom(administrateurDetails.getNom());
            administrateur.setPrenom(administrateurDetails.getPrenom());
            administrateur.setAdresse(administrateurDetails.getAdresse());
            administrateur.setTelephone1(administrateurDetails.getTelephone1());
            administrateur.setTelephone2(administrateurDetails.getTelephone2());
            administrateur.setEmail(administrateurDetails.getEmail());
            administrateur.setPaiements(administrateurDetails.getPaiements());
            administrateur.setResidences(administrateurDetails.getResidences());


            return administrateurRepository.save(administrateur);
        }).orElseThrow(() -> new RuntimeException("Administrateur non trouvé avec l'ID : " + id));
    }

    // Supprimer un administrateur
    public void deleteAdministrateur(Long id) {
        if (administrateurRepository.existsById(id)) {
            administrateurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Administrateur non trouvé avec l'ID : " + id);
        }
    }
}
