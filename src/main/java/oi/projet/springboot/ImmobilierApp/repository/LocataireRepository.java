package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.projet.springboot.ImmobilierApp.models.Locataire;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocataireRepository extends UserRepository<Locataire> {
    Locataire findByUsername(String username);
    Optional<Locataire> findByNomAndPrenom(String nomLocataire, String prenomLocataire);
}

