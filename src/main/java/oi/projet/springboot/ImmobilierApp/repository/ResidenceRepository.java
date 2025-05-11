package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenceRepository  extends JpaRepository<Residence, Long>{

    Optional<Residence> findByNomResidence(String nomResidence);
}
