package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/")
    public ResponseEntity<Ride> save(@RequestBody Ride ride){
        return ResponseEntity.ok(rideService.save(ride));
    }

    @GetMapping("/")
    public ResponseEntity<List<Ride>> getAll(){
        List<Ride>rides = rideService.getAll();
        if (rides.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(rides);
    }

}
