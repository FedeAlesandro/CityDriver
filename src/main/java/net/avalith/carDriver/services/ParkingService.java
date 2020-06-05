package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Parking;
import net.avalith.carDriver.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public Parking save(Parking parking){
        return parkingRepository.save(parking);
    }

    public List<Parking> getAll(){
        return parkingRepository.findAll();
    }
}
