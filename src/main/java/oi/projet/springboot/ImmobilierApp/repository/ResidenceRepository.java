package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import oi.projet.springboot.ImmobilierApp.models.Residence;

public interface ResidenceRepository  extends JpaRepository<Residence, Long>{

}
