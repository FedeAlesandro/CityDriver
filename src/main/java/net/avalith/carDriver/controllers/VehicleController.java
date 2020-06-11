package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.dtos.requests.VehicleDtoRequest;
import net.avalith.carDriver.models.dtos.responses.VehicleDtoResponse;
import net.avalith.carDriver.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    public ResponseEntity<List<VehicleDtoResponse>> getAll(){
        List<Vehicle> listVehicles = vehicleService.getAll();
        if (listVehicles.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listVehicles.stream()
                .map(VehicleDtoResponse::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<VehicleDtoResponse> save(@RequestBody VehicleDtoRequest vehicle){

        return ResponseEntity.status(HttpStatus.CREATED).body(new VehicleDtoResponse(vehicleService.save(vehicle)));
    }

    @PutMapping("/{domain}")
    public ResponseEntity<VehicleDtoResponse> update (@RequestBody VehicleDtoRequest vehicleDtoRequest, @PathVariable String domain){

        return ResponseEntity.ok(new VehicleDtoResponse(vehicleService.update(vehicleDtoRequest,domain)));
    }

    @DeleteMapping("/{domain}")
    public ResponseEntity<Void>delete(@PathVariable String domain){
        vehicleService.delete(domain);

        return ResponseEntity.ok().build();
    }


}
