package oi.projet.springboot.ImmobilierApp.controllers;

import oi.projet.springboot.ImmobilierApp.models.CompteUser;
import oi.projet.springboot.ImmobilierApp.Services.CompteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compteUsers")
@CrossOrigin(origins = "*")
public class CompteUserController {

    @Autowired
    private CompteUserService compteUserService;

    @GetMapping
    public ResponseEntity<List<CompteUser>> getAllCompteUsers() {
        return ResponseEntity.ok(compteUserService.getAllComptes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteUser> getCompteUserById(@PathVariable Long id) {
        return compteUserService.getCompteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompteUser> createCompteUser(@RequestBody CompteUser compteUser) {
        return ResponseEntity.ok(compteUserService.saveCompte(compteUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteUser> updateCompteUser(@PathVariable Long id, @RequestBody CompteUser compteUser) {
        compteUser.setUserid(id);
        return ResponseEntity.ok(compteUserService.saveCompte(compteUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompteUser(@PathVariable Long id) {
        compteUserService.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }
}
