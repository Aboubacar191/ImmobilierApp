package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.CompteUser;
import oi.projet.springboot.ImmobilierApp.repository.CompteUserRepository;

@Service
public class CompteUserService {

    @Autowired
    private CompteUserRepository compteUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injecter le PasswordEncoder

    public List<CompteUser> getAllComptes() {
        return compteUserRepository.findAll();
    }

    public Optional<CompteUser> getCompteById(Long id) {
        return compteUserRepository.findById(id);
    }

    public CompteUser saveCompte(CompteUser compteUser) {
        // Vérifier si le mot de passe est déjà haché (éviter un double hachage)
        if (!compteUser.getPassword().startsWith("$2a$")) {
            compteUser.setPassword(passwordEncoder.encode(compteUser.getPassword())); // Hachage du mot de passe
        }
        return compteUserRepository.save(compteUser);
    }

    public void deleteCompte(Long id) {
        if (compteUserRepository.existsById(id)) {
            compteUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("Compte introuvable avec l'ID : " + id);
        }
    }
}
