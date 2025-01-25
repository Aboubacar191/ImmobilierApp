package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import oi.projet.springboot.ImmobilierApp.Services.LocataireService;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.repository.UtilisateurRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LocataireController {

    private final PasswordEncoder passwordEncoder;
   

    @Autowired
    private LocataireService locataireService;

    @RequestMapping(method = RequestMethod.GET, value = "/locataires")
    public ResponseEntity<List<Locataire>> getLocataires() {
        List<Locataire> locataires = locataireService.getLocataires();
        return new ResponseEntity<>(locataires, HttpStatus.OK);
    }

    @RequestMapping("/locataires/{id}")
    public ResponseEntity<Locataire> getLocataire(@PathVariable long id) {
        Locataire locataire = locataireService.getUnLocataire(id);
        return locataire != null 
            ? new ResponseEntity<>(locataire, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/locataires/{id}")
    public ResponseEntity<Void> deleteLocataire(@PathVariable long id) {
        locataireService.deleteLocataire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/locataires")
    public ResponseEntity<Void> addLocataire(@RequestBody Locataire locataire) {
        
        locataire.setMotDePasse(passwordEncoder.encode(locataire.getMotDePasse()));
        locataireService.addLocataire(locataire);
        return new ResponseEntity<>(HttpStatus.CREATED);
       
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/locataires/{id}")
    public ResponseEntity<Void> updateLocataire(@RequestBody Locataire locataire, @PathVariable long id) {
        locataire.setMotDePasse(passwordEncoder.encode(locataire.getMotDePasse()));
        locataireService.updateLocataire(locataire, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}