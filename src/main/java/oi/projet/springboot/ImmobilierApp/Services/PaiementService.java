package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.repository.PaiementRepository;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> getPaiements() {
        List<Paiement> paiements = new ArrayList<>();
        paiementRepository.findAll().forEach(paiements::add);
        return paiements;
    }

    public Paiement getUnPaiement(long idPaiement) {
        return paiementRepository.findById(idPaiement).orElse(null);
    }

    public void deletePaiement(long idPaiement) {
        paiementRepository.deleteById(idPaiement);
    }

    public void addPaiement(Paiement paiement) {
        paiementRepository.save(paiement);
    }

    public void updatePaiement(Paiement paiement, long idPaiement) {
        Optional<Paiement> existingPaiementOptional = paiementRepository.findById(idPaiement);

        if (existingPaiementOptional.isPresent()) {
            Paiement existingPaiement = existingPaiementOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingPaiement.setDate(paiement.getDate());
            existingPaiement.setMontant(paiement.getMontant());
            existingPaiement.setMethodeDePaiement(paiement.getMethodeDePaiement());
            existingPaiement.setNumeroDuVirement(paiement.getNumeroDuVirement());
            existingPaiement.setStatut(paiement.getStatut());

            // Sauvegardez l'entité mise à jour
            paiementRepository.save(existingPaiement);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Paiement with id " + idPaiement + " not found.");
        }
    }
}

