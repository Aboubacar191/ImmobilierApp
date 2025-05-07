package oi.projet.springboot.ImmobilierApp.Services;

import oi.projet.springboot.ImmobilierApp.DTO.ResidenceNomDTO;
import oi.projet.springboot.ImmobilierApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    // Récupérer tous les administrateurs (quel que soit leur rôle : Administrateur ou Gestionnaire)
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    // Récupérer les administrateurs ayant le rôle "Administrateur"
    public List<Administrateur> getOnlyAdministrateurs() {
        return administrateurRepository.findByRole(User.Role.Administrateur);
    }

    // Récupérer les administrateurs ayant le rôle "Gestionnaire"
    public List<Administrateur> getGestionnaires() {
        return administrateurRepository.findByRole(User.Role.Gestionnaire);
    }

    // Récupérer un administrateur par ID
    public Optional<Administrateur> getAdministrateurById(Long id) {
        return administrateurRepository.findById(id);
    }

    // Ajouter un administrateur ou gestionnaire
    public Administrateur saveAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    // Mettre à jour un administrateur
    public Administrateur updateAdministrateur(Long id, Administrateur administrateurDetails) {
        return administrateurRepository.findById(id).map(administrateur -> {
            administrateur.setImageURL(administrateurDetails.getImageURL());
            administrateur.setNom(administrateurDetails.getNom());
            administrateur.setPrenom(administrateurDetails.getPrenom());
            administrateur.setAdresse(administrateurDetails.getAdresse());
            administrateur.setTelephone1(administrateurDetails.getTelephone1());
            administrateur.setTelephone2(administrateurDetails.getTelephone2());
            administrateur.setEmail(administrateurDetails.getEmail());
            administrateur.setResidences(administrateurDetails.getResidences());
            administrateur.setRole(administrateurDetails.getRole()); // mise à jour du rôle

            return administrateurRepository.save(administrateur);
        }).orElseThrow(() -> new RuntimeException("Administrateur non trouvé avec l'ID : " + id));

    }

    public List<ResidenceNomDTO> getNomResidencesByAdminId(Long adminId) {
        Administrateur admin = administrateurRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));

        return admin.getResidences().stream()
                .map(res -> new ResidenceNomDTO(res.getNomResidence()))
                .collect(Collectors.toList());
    }


    // Supprimer
    public void deleteAdministrateur(Long id) {
        if (administrateurRepository.existsById(id)) {
            administrateurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Administrateur non trouvé avec l'ID : " + id);
        }
    }
}
