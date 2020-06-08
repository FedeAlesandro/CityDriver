package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.VehicleModels;
import net.avalith.carDriver.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/vehicle-models")
@RestController
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping("/")
    public ResponseEntity<List<VehicleModels>> getAll(){
        List<VehicleModels> listVehicleModels = vehicleModelService.getAll();
        if (listVehicleModels.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listVehicleModels);
    }

    @PostMapping("/brand/{brand}")
    public ResponseEntity<VehicleModels> save(@RequestBody VehicleModels vehicle, @PathVariable String brand){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleModelService.save(vehicle,brand));
    }
}
