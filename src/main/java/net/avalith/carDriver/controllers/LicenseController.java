package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.License;
import net.avalith.carDriver.models.dtos.requests.LicenseDtoRequest;
import net.avalith.carDriver.models.dtos.responses.LicenseDtoResponse;
import net.avalith.carDriver.services.LicenseService;
import net.avalith.carDriver.utils.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Routes.LICENSE, consumes = Routes.MEDIA_TYPE)
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @PostMapping
    public ResponseEntity<LicenseDtoResponse> save(@RequestBody @Valid LicenseDtoRequest license){

        return ResponseEntity.status(HttpStatus.CREATED).body(new LicenseDtoResponse(licenseService.save(license)));
    }

    @GetMapping(produces = Routes.MEDIA_TYPE)
    public ResponseEntity<List<LicenseDtoResponse>> getAll(){
        List<License> licenses = licenseService.getAll();

        if (licenses.isEmpty())
            return ResponseEntity.noContent().build();

        List<LicenseDtoResponse> licenseResponses = licenses.stream()
                .map(LicenseDtoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(licenseResponses);
    }

    @PutMapping(value = Routes.LICENSE_UPDATE)
    public ResponseEntity<LicenseDtoResponse> update(@PathVariable(value = "number") String number, @RequestBody @Valid LicenseDtoRequest license){

        return ResponseEntity.ok(new LicenseDtoResponse(licenseService.update(number, license)));
    }
}
