package oi.projet.springboot.ImmobilierApp.repository;

import oi.projet.springboot.ImmobilierApp.models.User;

import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdministrateurRepository extends UserRepository<Administrateur> {
    Administrateur findByUsername(String username);
    List<Administrateur> findByRole(User.Role role);

}

