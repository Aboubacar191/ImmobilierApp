package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.projet.springboot.ImmobilierApp.models.GestionnaireDeResidence;

public interface GestionnaireRepository extends JpaRepository<GestionnaireDeResidence, Long>{


}
