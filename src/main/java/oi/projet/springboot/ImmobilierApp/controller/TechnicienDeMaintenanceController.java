package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import oi.projet.springboot.ImmobilierApp.Services.TechnicienService;
import oi.projet.springboot.ImmobilierApp.models.TechnicienDeMaintenance;
import oi.projet.springboot.ImmobilierApp.repository.UtilisateurRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TechnicienDeMaintenanceController {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private TechnicienService technicienService;

    @RequestMapping(method = RequestMethod.GET, value = "/techniciens")
    public ResponseEntity<List<TechnicienDeMaintenance>> getTechniciens() {
        List<TechnicienDeMaintenance> techniciens = technicienService.getTechniciens();
        return new ResponseEntity<>(techniciens, HttpStatus.OK);
    }

    @RequestMapping("/techniciens/{id}")
    public ResponseEntity<TechnicienDeMaintenance> getTechnicien(@PathVariable long id) {
        TechnicienDeMaintenance technicien = technicienService.getUnTechnicien(id);
        return technicien != null 
            ? new ResponseEntity<>(technicien, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/techniciens/{id}")
    public ResponseEntity<Void> deleteTechnicien(@PathVariable long id) {
        technicienService.deleteTechnicien(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/techniciens")
    public ResponseEntity<Void> addTechnicien(@RequestBody TechnicienDeMaintenance technicien) {
        
            technicien.setMotDePasse(passwordEncoder.encode(technicien.getMotDePasse()));
            technicienService.addTechnicien(technicien);
            return new ResponseEntity<>(HttpStatus.CREATED);
       
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/techniciens/{id}")
    public ResponseEntity<Void> updateTechnicien(@RequestBody TechnicienDeMaintenance technicien, @PathVariable long id) {
        technicien.setMotDePasse(passwordEncoder.encode(technicien.getMotDePasse()));
        technicienService.updateTechnicien(technicien, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
