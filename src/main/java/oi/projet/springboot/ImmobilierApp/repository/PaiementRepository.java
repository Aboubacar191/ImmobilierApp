package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.projet.springboot.ImmobilierApp.models.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long>{

}
