package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.dtos.requests.RideDtoRequest;
import net.avalith.carDriver.repositories.PointRepository;
import net.avalith.carDriver.repositories.RideRepository;
import net.avalith.carDriver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public Ride save(RideDtoRequest ride){
        RidePointDto ridePoint = ride.getOriginPoint();
        pointRepository.findByCoordinateLatitudeAndCoordinateLongitude(ridePoint.getCoordinateLatitude(), ridePoint.getCoordinateLogitude())
            .orElseThrow(RuntimeException::new); //todo hacer excepcion custom
        //todavia no terminada
        return rideRepository.save(ride);
    }

    public List<Ride> getAll(){
        return rideRepository.findAll();
    }
}
