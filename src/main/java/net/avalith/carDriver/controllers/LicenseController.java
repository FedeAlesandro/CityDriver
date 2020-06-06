package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.License;
import net.avalith.carDriver.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @PostMapping("/")
    public ResponseEntity<License> save(@RequestBody License license){
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.save(license));
    }

    @GetMapping("/")
    public ResponseEntity<List<License>> getAll(){
        List<License> licenses = licenseService.getAll();
        if (licenses.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(licenses);
    }
}
