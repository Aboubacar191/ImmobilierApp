package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.TechnicienDeMaintenance;
import oi.projet.springboot.ImmobilierApp.repository.TechnicienRepository;

@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    public List<TechnicienDeMaintenance> getTechniciens() {
        List<TechnicienDeMaintenance> techniciens = new ArrayList<>();
        technicienRepository.findAll().forEach(techniciens::add);
        return techniciens;
    }

    public TechnicienDeMaintenance getUnTechnicien(long id) {
        return technicienRepository.findById(id).orElse(null);
    }

    public void deleteTechnicien(long id) {
        technicienRepository.deleteById(id);
    }

    public void addTechnicien(TechnicienDeMaintenance technicien) {
        technicienRepository.save(technicien);
    }

    public void updateTechnicien(TechnicienDeMaintenance technicien, long id) {
        Optional<TechnicienDeMaintenance> existingTechnicienOptional = technicienRepository.findById(id);

        if (existingTechnicienOptional.isPresent()) {
            TechnicienDeMaintenance existingTechnicien = existingTechnicienOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingTechnicien.setImage(technicien.getImage());
            existingTechnicien.setNom(technicien.getNom());
            existingTechnicien.setPrenom(technicien.getPrenom());
            existingTechnicien.setAdresse(technicien.getAdresse());
            existingTechnicien.setEmail(technicien.getEmail());
            existingTechnicien.setContact(technicien.getContact());
            existingTechnicien.setNomUtilisateur(technicien.getNomUtilisateur());
            existingTechnicien.setMotDePasse(technicien.getMotDePasse());
            existingTechnicien.setMetier(technicien.getMetier());

            // Sauvegardez l'entité mise à jour
            technicienRepository.save(existingTechnicien);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Technicien with id " + id + " not found.");
        }
    }
}
