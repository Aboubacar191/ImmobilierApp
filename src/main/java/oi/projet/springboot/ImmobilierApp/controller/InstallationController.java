package oi.projet.springboot.ImmobilierApp.controller;

import oi.projet.springboot.ImmobilierApp.models.Installation;
import oi.projet.springboot.ImmobilierApp.Services.InstallationService;
import oi.projet.springboot.ImmobilierApp.models.InstallationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/installations")
@CrossOrigin(origins = "*")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @GetMapping
    public ResponseEntity<List<Installation>> getAllInstallations() {
        return ResponseEntity.ok(installationService.getAllInstallations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Installation> getInstallationById(@PathVariable Long id) {
        return installationService.getInstallationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Installation> createInstallation(@RequestBody Installation installation) {
        return ResponseEntity.ok(installationService.saveInstallation(installation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Installation> updateInstallation(@PathVariable InstallationKey id, @RequestBody Installation installation) {
        installation.setInstallationKey(id);
        return ResponseEntity.ok(installationService.saveInstallation(installation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstallation(@PathVariable Long id) {
        installationService.deleteInstallation(id);
        return ResponseEntity.noContent().build();
    }
}
