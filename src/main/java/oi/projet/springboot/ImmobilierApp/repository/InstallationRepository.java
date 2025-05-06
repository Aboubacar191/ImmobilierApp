package oi.projet.springboot.ImmobilierApp.repository;

import oi.projet.springboot.ImmobilierApp.models.Installation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, Long> {
}
