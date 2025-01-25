package oi.projet.springboot.ImmobilierApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import oi.projet.springboot.ImmobilierApp.models.Maintenance;
import oi.projet.springboot.ImmobilierApp.repository.MaintenanceRepository;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public List<Maintenance> getMaintenances() {
        List<Maintenance> maintenances = new ArrayList<>();
        maintenanceRepository.findAll().forEach(maintenances::add);
        return maintenances;
    }

    public Maintenance getUneMaintenance(long id) {
        return maintenanceRepository.findById(id).orElse(null);
    }

    public void deleteMaintenance(long id) {
        maintenanceRepository.deleteById(id);
    }

    public void addMaintenance(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    public void updateMaintenance(Maintenance maintenance, long id) {
        Optional<Maintenance> existingMaintenanceOptional = maintenanceRepository.findById(id);

        if (existingMaintenanceOptional.isPresent()) {
            Maintenance existingMaintenance = existingMaintenanceOptional.get();
            // Mettez à jour les champs nécessaires de l'entité existante
            existingMaintenance.setNomMaintenance(maintenance.getNomMaintenance());
            existingMaintenance.setDateM(maintenance.getDateM());
            existingMaintenance.setDescription(maintenance.getDescription());
            existingMaintenance.setStatut(maintenance.getStatut());

            // Sauvegardez l'entité mise à jour
            maintenanceRepository.save(existingMaintenance);
        } else {
            // Gérer le cas où l'entité n'existe pas (par exemple, lever une exception)
            throw new EntityNotFoundException("Maintenance with id " + id + " not found.");
        }
    }
}
