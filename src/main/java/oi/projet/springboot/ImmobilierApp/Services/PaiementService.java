package oi.projet.springboot.ImmobilierApp.Services;

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
