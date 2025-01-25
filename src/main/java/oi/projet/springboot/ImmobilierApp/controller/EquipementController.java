package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import oi.projet.springboot.ImmobilierApp.Services.EquipementService;
import oi.projet.springboot.ImmobilierApp.models.Equipement;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    @RequestMapping(method = RequestMethod.GET, value = "/equipements")
    public ResponseEntity<List<Equipement>> getEquipements() {
        List<Equipement> equipements = equipementService.getEquipements();
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @RequestMapping("/equipements/{id}")
    public ResponseEntity<Equipement> getEquipement(@PathVariable long id) {
        Equipement equipement = equipementService.getUnEquipement(id);
        return equipement != null 
            ? new ResponseEntity<>(equipement, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/equipements/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable long id) {
        equipementService.deleteEquipement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/equipements")
    public ResponseEntity<Void> addEquipement(@RequestBody Equipement equipement) {
        equipementService.addEquipement(equipement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/equipements/{id}")
    public ResponseEntity<Void> updateEquipement(@RequestBody Equipement equipement, @PathVariable long id) {
        equipementService.updateEquipement(equipement, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
