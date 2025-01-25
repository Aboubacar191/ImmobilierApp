package oi.projet.springboot.ImmobilierApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.projet.springboot.ImmobilierApp.models.TechnicienDeMaintenance;
import java.util.List;


public interface TechnicienRepository extends JpaRepository<TechnicienDeMaintenance, Long>{


}
