package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.dtos.requests.RideDtoRequest;
import net.avalith.carDriver.repositories.PointRepository;
import net.avalith.carDriver.repositories.RideRepository;
import net.avalith.carDriver.repositories.UserRepository;
import net.avalith.carDriver.repositories.VehicleRepository;
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
        Vehicle vehicle = vehicleRepository.findByDomain(ride.getVehicleDomain())
                .orElseThrow(RuntimeException::new); //todo hacer excepcion custom
        Point point = pointRepository.findByCoordinateLatitudeAndCoordinateLongitude(ridePoint.getCoordinateLatitude(), ridePoint.getCoordinateLogitude())
                .orElseThrow(RuntimeException::new); //todo hacer excepcion custom
        User user = userRepository.findByDni(ride.getUserDni())
                .orElseThrow(RuntimeException::new); //todo hacer excepcion custom
        Ride rideToSave = Ride.fromRideDtoRequest(ride, vehicle, point, user);

        return rideRepository.save(rideToSave);
    }

    public List<Ride> getAll(){
        return rideRepository.findAll();
    }
}
