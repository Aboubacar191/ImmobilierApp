package oi.projet.springboot.ImmobilierApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import oi.projet.springboot.ImmobilierApp.Services.MaintenanceService;
import oi.projet.springboot.ImmobilierApp.models.Maintenance;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController

public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @RequestMapping(method = RequestMethod.GET, value = "/maintenances")
    public ResponseEntity<List<Maintenance>> getMaintenances() {
        List<Maintenance> maintenances = maintenanceService.getMaintenances();
        return new ResponseEntity<>(maintenances, HttpStatus.OK);
    }

    @RequestMapping("/maintenances/{id}")
    public ResponseEntity<Maintenance> getMaintenance(@PathVariable long id) {
        Maintenance maintenance = maintenanceService.getUneMaintenance(id);
        return maintenance != null 
            ? new ResponseEntity<>(maintenance, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/maintenances/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable long id) {
        maintenanceService.deleteMaintenance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/maintenances")
    public ResponseEntity<Void> addMaintenance(@RequestBody Maintenance maintenance) {
        maintenanceService.addMaintenance(maintenance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/maintenances/{id}")
    public ResponseEntity<Void> updateMaintenance(@RequestBody Maintenance maintenance, @PathVariable long id) {
        maintenanceService.updateMaintenance(maintenance, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
