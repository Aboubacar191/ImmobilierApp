package oi.projet.springboot.ImmobilierApp.controller;

import java.util.HashMap;
import java.util.Map;

import oi.projet.springboot.ImmobilierApp.configuration.JwtUtils;

import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.models.User;
import oi.projet.springboot.ImmobilierApp.repository.AdministrateurRepository;
import oi.projet.springboot.ImmobilierApp.repository.LocataireRepository;
import oi.projet.springboot.ImmobilierApp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.management.relation.Role;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AdministrateurRepository administrateurRepository;
    private final LocataireRepository locataireRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User compteUser) {
        String username = compteUser.getUsername();

        User existingUser = null;
        String typeUser = null;

        // Recherche d'abord dans les administrateurs
        Administrateur admin = administrateurRepository.findByUsername(username);
        if (admin != null) {
            existingUser = admin;
            typeUser = admin.getRole().name(); // Peut être "Administrateur" ou "Gestionnaire"
        } else {
            // Recherche ensuite dans les locataires
            Locataire locataire = locataireRepository.findByUsername(username);
            if (locataire != null) {
                existingUser = locataire;
                typeUser = "Locataire";
            }
        }

        if (existingUser == null) {
            log.warn("Tentative de connexion avec un nom d'utilisateur inconnu: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur incorrect");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, compteUser.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtils.generateToken(username);

            Map<String, Object> authData = new HashMap<>();
            authData.put("token", token);
            authData.put("TypeUser", typeUser); // "Administrateur", "Gestionnaire" ou "Locataire"
            authData.put("id", existingUser.getId());

            return ResponseEntity.ok(authData);

        } catch (BadCredentialsException e) {
            log.warn("Mot de passe incorrect pour l'utilisateur: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        } catch (Exception e) {
            log.error("Erreur d'authentification: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }

}
