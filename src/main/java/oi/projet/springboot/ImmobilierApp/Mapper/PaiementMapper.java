package oi.projet.springboot.ImmobilierApp.Mapper;

import oi.projet.springboot.ImmobilierApp.DTO.PaiementDTO;
import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    // Convertir une entité vers un DTO
    public PaiementDTO toDTO(Paiement paiement) {
        if (paiement == null) return null;

        String nom = paiement.getLocataire() != null ? paiement.getLocataire().getNom() : null;
        String prenom = paiement.getLocataire() != null ? paiement.getLocataire().getPrenom() : null;

        return new PaiementDTO(
                paiement.getIdPaiement(),
                paiement.getDate(),
                paiement.getMontant(),
                paiement.getMethodeDepaiement().name(),
                paiement.getStatut().name(),
                nom,
                prenom
        );
    }

    // Convertir un DTO vers une entité
    public Paiement toEntity(PaiementDTO dto, Locataire locataire) {
        if (dto == null) return null;

        Paiement paiement = new Paiement();
        paiement.setIdPaiement(dto.getIdPaiement());
        paiement.setDate(dto.getDate());
        paiement.setMontant(dto.getMontant());
        paiement.setMethodeDepaiement(Paiement.method.valueOf(dto.getMethodeDepaiement()));
        paiement.setStatut(Paiement.Statut.valueOf(dto.getStatut()));
        paiement.setLocataire(locataire);
        return paiement;
    }
}

