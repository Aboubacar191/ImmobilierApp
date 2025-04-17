package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.Installation;
import oi.projet.springboot.ImmobilierApp.repository.InstallationRepository;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepository installationRepository;

    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }

    public Optional<Installation> getInstallationById(Long id) {
        return installationRepository.findById(id);
    }

    public Installation saveInstallation(Installation installation) {
        return installationRepository.save(installation);
    }

    public void deleteInstallation(Long id) {
        if (installationRepository.existsById(id)) {
            installationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Installation introuvable avec l'ID : " + id);
        }
    }
}
