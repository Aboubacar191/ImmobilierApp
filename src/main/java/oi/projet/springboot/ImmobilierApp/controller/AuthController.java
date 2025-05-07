package oi.projet.springboot.ImmobilierApp.controller;


import oi.projet.springboot.ImmobilierApp.configuration.JwtUtils;
import oi.projet.springboot.ImmobilierApp.models.*;
import oi.projet.springboot.ImmobilierApp.repository.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AdministrateurRepository administrateurRepository;
    private final LocataireRepository locataireRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JwtUtils jwtUtils,
                          AdministrateurRepository administrateurRepository,
                          LocataireRepository locataireRepository,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.administrateurRepository = administrateurRepository;
        this.locataireRepository = locataireRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Locataire newUser) {
        // Vérification unicité du username
        if (administrateurRepository.findByUsername(newUser.getUsername()) != null ||
                locataireRepository.findByUsername(newUser.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Nom d'utilisateur déjà existant");
        }

        // Encodage du mot de passe
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // Création et sauvegarde selon le rôle
        if (newUser.getRole() == User.Role.Locataire) {
            Locataire locataire = new Locataire();
            locataire.setUsername(newUser.getUsername());
            locataire.setPassword(newUser.getPassword());
            locataire.setNom(newUser.getNom());
            locataire.setPrenom(newUser.getPrenom());
            locataire.setAdresse(newUser.getAdresse());
            locataire.setImageURL(newUser.getImageURL());
            locataire.setTelephone1(newUser.getTelephone1());
            locataire.setTelephone2(newUser.getTelephone2());
            locataire.setEmail(newUser.getEmail());
            locataire.setRole(User.Role.Locataire);
            locataire.setNumeroDuLit(newUser.getNumeroDuLit());
            locataire.setContrat(newUser.getContrat());

            // Champs spécifiques comme contrat ou numéro de lit peuvent être ajoutés si disponibles

            return ResponseEntity.ok(locataireRepository.save(locataire));
        } else {
            Administrateur admin = new Administrateur();
            admin.setUsername(newUser.getUsername());
            admin.setPassword(newUser.getPassword());
            admin.setNom(newUser.getNom());
            admin.setPrenom(newUser.getPrenom());
            admin.setAdresse(newUser.getAdresse());
            admin.setImageURL(newUser.getImageURL());
            admin.setTelephone1(newUser.getTelephone1());
            admin.setTelephone2(newUser.getTelephone2());
            admin.setEmail(newUser.getEmail());
            admin.setRole(newUser.getRole());

            return ResponseEntity.ok(administrateurRepository.save(admin));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Locataire compteUser) {
        String username = compteUser.getUsername();
        User existingUser = null;
        String typeUser = null;

        // Recherche du compte utilisateur
        Administrateur admin = administrateurRepository.findByUsername(username);
        if (admin != null) {
            existingUser = admin;
            typeUser = admin.getRole().name();
        } else {
            Locataire locataire = locataireRepository.findByUsername(username);
            if (locataire != null) {
                existingUser = locataire;
                typeUser = "Locataire";
            }
        }

        if (existingUser == null) {
            log.warn("Tentative de connexion avec un nom d'utilisateur inconnu : {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur incorrect");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, compteUser.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtils.generateToken(username);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("TypeUser", typeUser);
            response.put("id", existingUser.getId());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            log.warn("Mot de passe incorrect pour l'utilisateur : {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        } catch (Exception e) {
            log.error("Erreur d'authentification : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }
}
