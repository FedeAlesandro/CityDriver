package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.dtos.requests.VehicleDtoRequest;
import net.avalith.carDriver.services.VehicleService;
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
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAll(){
        List<Vehicle> listVehicles = vehicleService.getAll();
        if (listVehicles.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listVehicles);
    }

    @PostMapping("/")
    public ResponseEntity<Vehicle> save(@RequestBody VehicleDtoRequest vehicle){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.save(vehicle));
    }

}
