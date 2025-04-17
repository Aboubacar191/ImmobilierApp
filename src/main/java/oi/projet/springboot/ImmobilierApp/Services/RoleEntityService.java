package oi.projet.springboot.ImmobilierApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import oi.projet.springboot.ImmobilierApp.models.RoleEntity;
import oi.projet.springboot.ImmobilierApp.repository.RoleEntityRepository;

@Service
public class RoleEntityService {

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    public List<RoleEntity> getAllRoles() {
        return roleEntityRepository.findAll();
    }

    public Optional<RoleEntity> getRoleById(Long id) {
        return roleEntityRepository.findById(id);
    }

    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleEntityRepository.save(roleEntity);
    }

    public void deleteRole(Long id) {
        if (roleEntityRepository.existsById(id)) {
            roleEntityRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rôle introuvable avec l'ID : " + id);
        }
    }
}
