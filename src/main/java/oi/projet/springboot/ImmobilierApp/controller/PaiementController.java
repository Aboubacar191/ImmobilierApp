package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import oi.projet.springboot.ImmobilierApp.Services.PaiementService;
import oi.projet.springboot.ImmobilierApp.models.Paiement;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController

public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @RequestMapping(method = RequestMethod.GET, value = "/paiements")
    public ResponseEntity<List<Paiement>> getPaiements() {
        List<Paiement> paiements = paiementService.getPaiements();
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }

    @RequestMapping("/paiements/{id}")
    public ResponseEntity<Paiement> getPaiement(@PathVariable long id) {
        Paiement paiement = paiementService.getUnPaiement(id);
        return paiement != null 
            ? new ResponseEntity<>(paiement, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/paiements/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable long id) {
        paiementService.deletePaiement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/paiements")
    public ResponseEntity<Void> addPaiement(@RequestBody Paiement paiement) {
        paiementService.addPaiement(paiement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/paiements/{id}")
    public ResponseEntity<Void> updatePaiement(@RequestBody Paiement paiement, @PathVariable long id) {
        paiementService.updatePaiement(paiement, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
