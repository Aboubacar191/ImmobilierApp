package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.projet.springboot.ImmobilierApp.models.Utilisateur;



public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
   
    Utilisateur findByNomUtilisateur(String nomUtilisateur);
}
