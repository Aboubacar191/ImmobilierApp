package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import oi.projet.springboot.ImmobilierApp.Services.AdministrateurService;
import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.repository.UtilisateurRepository;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class AdministrateurController {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private AdministrateurService administrateurService;

    @RequestMapping(method = RequestMethod.GET, value = "/administrateurs")
    public ResponseEntity<List<Administrateur>> getAdministrateurs() {
        List<Administrateur> administrateurs = administrateurService.getAdministrateurs();
        return new ResponseEntity<>(administrateurs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/administrateurs/{id}")
    public ResponseEntity<Administrateur> getAdministrateur(@PathVariable long id) {
        Administrateur administrateur = administrateurService.getUnAdministrateur(id);
        return administrateur != null 
            ? new ResponseEntity<>(administrateur, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/administrateurs/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable long id) {
        administrateurService.deleteAdministrateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/administrateurs")
    public ResponseEntity<Void> addAdministrateur(@RequestBody Administrateur administrateur) {
        administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));
        administrateurService.addAdministrateur(administrateur);
        return new ResponseEntity<>(HttpStatus.CREATED);
      
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/administrateurs/{id}")
    public ResponseEntity<Void> updateAdministrateur(@RequestBody Administrateur administrateur, @PathVariable long id) {
        administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));
        administrateurService.updateAdministrateur(administrateur, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
