package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.Services.RoleEntityService;
import oi.projet.springboot.ImmobilierApp.models.RoleEntity;
import oi.projet.springboot.ImmobilierApp.Services.RoleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleEntityController {

    @Autowired
    private RoleEntityService roleEntityService;

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        return ResponseEntity.ok(roleEntityService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id) {
        Optional<RoleEntity> role = roleEntityService.getRoleById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role) {
        return ResponseEntity.ok(roleEntityService.saveRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable Long id, @RequestBody RoleEntity role) {
        role.setRoleId(id);
        return ResponseEntity.ok(roleEntityService.saveRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleEntityService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
