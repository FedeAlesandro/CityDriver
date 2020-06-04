package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Parking;
import net.avalith.carDriver.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/")
    public ResponseEntity<Parking> save(Parking parking){
        return ResponseEntity.ok(parkingService.save(parking));
    }

    @GetMapping("/")
    public ResponseEntity<List<Parking>> getAll(){
        List<Parking>parkings = parkingService.getAll();
        if (parkings.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(parkings);
    }
}
