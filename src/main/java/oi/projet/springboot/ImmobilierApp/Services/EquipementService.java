package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.Equipement;
import oi.projet.springboot.ImmobilierApp.repository.EquipementRepository;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    public List<Equipement> getEquipements() {
        List<Equipement> equipements = new ArrayList<Equipement>();
        equipementRepository.findAll().forEach(equipement -> {
            equipements.add(equipement);
        });
        return equipements;
    }

    public Equipement getUnEquipement(long idEquipement) {
        return equipementRepository.findById(idEquipement).orElse(null);
    }

    public void deleteEquipement(long idEquipement) {
        equipementRepository.deleteById(idEquipement);
    }

    public void addEquipement(Equipement equipement) {
        equipementRepository.save(equipement);

    }

    public void updateEquipement(Equipement equipement, long idEquipement) {
        Optional<Equipement> existingEquipementOptional = equipementRepository.findById(idEquipement);

        if (existingEquipementOptional.isPresent()) {
            Equipement existingEquipement = existingEquipementOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingEquipement.setNomEquipement(equipement.getNomEquipement());
            existingEquipement.setDescription(equipement.getDescription());
            existingEquipement.setQuantité(equipement.getQuantité());
            existingEquipement.setNomEmplacement(equipement.getNomEmplacement());

            // Sauvegardez l'entité mise à jour
            equipementRepository.save(existingEquipement);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Equipement with id " + idEquipement + " not found.");
        }
    }
}
