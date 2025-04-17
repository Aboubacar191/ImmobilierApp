package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Equipement;
import oi.projet.springboot.ImmobilierApp.repository.EquipementRepository;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    public Optional<Equipement> getEquipementById(Long id) {
        return equipementRepository.findById(id);
    }

    public Equipement saveEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public void deleteEquipement(Long id) {
        if (equipementRepository.existsById(id)) {
            equipementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Équipement introuvable avec l'ID : " + id);
        }
    }
}
