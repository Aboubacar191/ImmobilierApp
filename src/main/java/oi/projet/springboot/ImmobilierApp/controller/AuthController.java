package oi.projet.springboot.ImmobilierApp.controller;

import java.util.HashMap;
import java.util.Map;

import oi.projet.springboot.ImmobilierApp.models.CompteUser;
import oi.projet.springboot.ImmobilierApp.repository.CompteUserRepository;
import oi.projet.springboot.ImmobilierApp.configuration.JwtUtils;

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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtUtils jwtUtils;
    private final CompteUserRepository compteUserRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody CompteUser compteUser) {
        // Vérifier si l'utilisateur existe
        CompteUser existingUser = compteUserRepository.findByUsername(compteUser.getUsername()).orElse(null);

        if (existingUser == null) {
            log.warn("Tentative de connexion avec un nom d'utilisateur inconnu: {}", compteUser.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur incorrect");
        }

        try {
            // Authentification
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(compteUser.getUsername(), compteUser.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Générer le token JWT
            String token = jwtUtils.generateToken(authentication.getName());

            // Préparer les données de réponse
            Map<String, Object> authData = new HashMap<>();
            authData.put("token", token);
            authData.put("id", existingUser.getUserid());
            authData.put("TypeUser", existingUser.getRoleEntities().getName());

            return ResponseEntity.ok(authData);

        } catch (BadCredentialsException e) {
            log.warn("Mot de passe incorrect pour l'utilisateur: {}", compteUser.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        } catch (Exception e) {
            log.error("Erreur d'authentification: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }
}
