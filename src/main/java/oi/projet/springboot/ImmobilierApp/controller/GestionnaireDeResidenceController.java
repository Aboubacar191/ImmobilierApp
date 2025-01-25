package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import oi.projet.springboot.ImmobilierApp.Services.GestionnaireService;
import oi.projet.springboot.ImmobilierApp.models.GestionnaireDeResidence;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GestionnaireDeResidenceController {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    private GestionnaireService gestionnaireService;

    @RequestMapping(method = RequestMethod.GET, value = "/gestionnaires")
    public ResponseEntity<List<GestionnaireDeResidence>> getGestionnaires() {
        List<GestionnaireDeResidence> gestionnaires = gestionnaireService.getGestionnaires();
        return new ResponseEntity<>(gestionnaires, HttpStatus.OK);
    }

    @RequestMapping("/gestionnaires/{id}")
    public ResponseEntity<GestionnaireDeResidence> getGestionnaire(@PathVariable long id) {
        GestionnaireDeResidence gestionnaire = gestionnaireService.getUnGestionnaire(id);
        return gestionnaire != null 
            ? new ResponseEntity<>(gestionnaire, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/gestionnaires/{id}")
    public ResponseEntity<Void> deleteGestionnaire(@PathVariable long id) {
        gestionnaireService.deleteGestionnaire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/gestionnaires")
    public ResponseEntity<Void> addGestionnaire(@RequestBody GestionnaireDeResidence gestionnaire) {
        gestionnaire.setMotDePasse(passwordEncoder.encode(gestionnaire.getMotDePasse()));
        gestionnaireService.addGestionnaire(gestionnaire);
        return new ResponseEntity<>(HttpStatus.CREATED);
       
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/gestionnaires/{id}")
    public ResponseEntity<Void> updateGestionnaire(@RequestBody GestionnaireDeResidence gestionnaire, @PathVariable long id) {
        gestionnaire.setMotDePasse(passwordEncoder.encode(gestionnaire.getMotDePasse()));
        gestionnaireService.updateGestionnaire(gestionnaire, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
