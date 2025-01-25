package oi.projet.springboot.ImmobilierApp.controller;

import java.util.HashMap;
import java.util.Map;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.DiscriminatorValue;
import lombok.RequiredArgsConstructor;
import oi.projet.springboot.ImmobilierApp.configuration.JwtUtils;
import oi.projet.springboot.ImmobilierApp.models.Utilisateur;
import oi.projet.springboot.ImmobilierApp.repository.UtilisateurRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
public class AuthController {

    private final JwtUtils jwtUtils;
    private final UtilisateurRepository utilisateurRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Utilisateur utilisateur) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(utilisateur.getNomUtilisateur(), utilisateur.getMotDePasse())
            );
    
            if (authentication.isAuthenticated()) {
                // Récupérer l'utilisateur authentifié
                Utilisateur authenticatedUser = utilisateurRepository.findByNomUtilisateur(utilisateur.getNomUtilisateur());
    
                // Préparer les données de réponse
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(utilisateur.getNomUtilisateur()));
                authData.put("id", authenticatedUser.getId()); // Ajouter l'ID de l'utilisateur à la réponse
                authData.put("TypeUser", authenticatedUser.getClass().getAnnotation(DiscriminatorValue.class).value());
    
                return ResponseEntity.ok(authData);
            }
    
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        } catch (org.springframework.security.core.AuthenticationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    
    

}
