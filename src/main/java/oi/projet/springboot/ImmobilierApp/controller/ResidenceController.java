package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oi.projet.springboot.ImmobilierApp.Services.ResidenceService;
import oi.projet.springboot.ImmobilierApp.models.Residence;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @RequestMapping(method = RequestMethod.GET, value = "/residence")
    public ResponseEntity<List<Residence>> getResidence() {
        List<Residence> residences = residenceService.getResidences();
        return new ResponseEntity<>(residences, HttpStatus.OK);
    }

    @RequestMapping("/residence/{idResidence}")
    public ResponseEntity<Residence> getUneResidence(@PathVariable long idResidence) {
        Residence residence = residenceService.getUneResidence(idResidence);
        return residence != null 
            ? new ResponseEntity<>(residence, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "residence/{idResidence}")
    public ResponseEntity<Void> deleteResidence(@PathVariable long idResidence) {
        residenceService.deleteResidence(idResidence);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/residence")
    public ResponseEntity<Void> addResidence(@RequestBody Residence residence) {
        residenceService.addResidence(residence);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/residence/{idResidence}")
    public ResponseEntity<Void> updateResidence(@RequestBody Residence residence, @PathVariable long idResidence) {
        residenceService.updateResidence(residence, idResidence);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
