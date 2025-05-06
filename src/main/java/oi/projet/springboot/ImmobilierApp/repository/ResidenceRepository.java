package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenceRepository  extends JpaRepository<Residence, Long>{

}
