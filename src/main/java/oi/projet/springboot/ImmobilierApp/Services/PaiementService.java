package oi.projet.springboot.ImmobilierApp.Services;

import oi.projet.springboot.ImmobilierApp.DTO.PaiementDetailsDTO;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.repository.PaiementRepository;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> getPaiementById(Long id) {
        return paiementRepository.findById(id);
    }
    public Optional<PaiementDetailsDTO> getPaiementDetailsById(Long id) {
        Optional<Paiement> optionalPaiement = paiementRepository.findById(id);

        if (optionalPaiement.isPresent()) {
            Paiement paiement = optionalPaiement.get();

            // Supposons que Paiement a un champ Locataire locataire
            Locataire locataire = paiement.getLocataire();


            PaiementDetailsDTO dto = new PaiementDetailsDTO(
                    paiement.getIdPaiement(),
                    paiement.getDate(),
                    paiement.getMontant(),
                    paiement.getMethodeDepaiement().name(),
                    paiement.getStatut().name(),
                    locataire.getNom(),
                    locataire.getPrenom(),
                    locataire.getAdresse(),
                    locataire.getTelephone1(),
                    locataire.getEmail(),
                    locataire.getResidence().getNomResidence(),
                    locataire.getNumeroDuLit(),
                    locataire.getResidence().getTypeLogement().name()
            );

            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }


    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public void deletePaiement(Long id) {
        if (paiementRepository.existsById(id)) {
            paiementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paiement introuvable avec l'ID : " + id);
        }
    }
}
