package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.projet.springboot.ImmobilierApp.models.Locataire;

public interface LocataireRepository extends JpaRepository<Locataire, Long>{

  

}
