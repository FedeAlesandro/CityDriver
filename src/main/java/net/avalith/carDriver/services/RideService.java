package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride save(Ride ride){
        return rideRepository.save(ride);
    }

    public List<Ride> getAll(){
        return rideRepository.findAll();
    }
}
