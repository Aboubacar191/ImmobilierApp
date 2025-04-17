package oi.projet.springboot.ImmobilierApp.repository;

import oi.projet.springboot.ImmobilierApp.models.CompteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteUserRepository extends JpaRepository<CompteUser, Long> {
    Optional<CompteUser> findByUsername(String username);
}

