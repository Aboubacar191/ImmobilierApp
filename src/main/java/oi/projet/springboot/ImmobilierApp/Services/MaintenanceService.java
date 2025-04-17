package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Maintenance;
import oi.projet.springboot.ImmobilierApp.repository.MaintenanceRepository;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    public Optional<Maintenance> getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id);
    }

    public Maintenance saveMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public void deleteMaintenance(Long id) {
        if (maintenanceRepository.existsById(id)) {
            maintenanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Maintenance introuvable avec l'ID : " + id);
        }
    }
}
