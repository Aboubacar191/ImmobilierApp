package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Residence;
import oi.projet.springboot.ImmobilierApp.repository.ResidenceRepository;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    public List<Residence> getAllResidences() {
        return residenceRepository.findAll();
    }

    public Optional<Residence> getResidenceById(Long id) {
        return residenceRepository.findById(id);
    }

    public Residence saveResidence(Residence residence) {
        return residenceRepository.save(residence);
    }

    public void deleteResidence(Long id) {
        if (residenceRepository.existsById(id)) {
            residenceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Résidence introuvable avec l'ID : " + id);
        }
    }
}
