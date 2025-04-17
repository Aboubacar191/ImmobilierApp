package oi.projet.springboot.ImmobilierApp.repository;

import oi.projet.springboot.ImmobilierApp.models.Installation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallationRepository extends JpaRepository<Installation, Long> {
}
