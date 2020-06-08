package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.NotFoundException;
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

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_POINT;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_USER;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_VEHICLE;

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
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_VEHICLE));

        Point point = pointRepository.findByCoordinateLatitudeAndCoordinateLongitude(ridePoint.getCoordinateLatitude(), ridePoint.getCoordinateLongitude())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_POINT));

        User user = userRepository.findByDni(ride.getUserDni())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

        Ride rideToSave = Ride.fromRideDtoRequest(ride, vehicle, point, user);

        return rideRepository.save(rideToSave);
    }

    public List<Ride> getAll(){
        return rideRepository.findAll();
    }
}
