package net.avalith.carDriver.services;

import jdk.vm.ci.meta.Local;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.RideLog;
import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.VehicleCategory;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.dtos.requests.RideDtoRequest;
import net.avalith.carDriver.models.dtos.requests.RideDtoUpdateRequest;
import net.avalith.carDriver.models.enums.RideState;
import net.avalith.carDriver.repositories.PointRepository;
import net.avalith.carDriver.repositories.RideLogRepository;
import net.avalith.carDriver.repositories.RideRepository;
import net.avalith.carDriver.repositories.UserRepository;
import net.avalith.carDriver.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_POINT;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_RIDE;
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

    @Autowired
    private RideLogRepository rideLogRepository;

    public Ride save(RideDtoRequest ride){

        RidePointDto ridePoint = ride.getOriginPoint();

        Vehicle vehicle = vehicleRepository.findByDomain(ride.getVehicleDomain())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_VEHICLE));

        Point point = pointRepository.getByLatAndLng(ridePoint.getLat(), ridePoint.getLng())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_POINT));

        User user = userRepository.getByDni(ride.getUserDni())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

        Ride rideSaved = new Ride(ride, vehicle, point, user);

        String value = ride.getTariffType().getValue(ride.getTariffType());

        rideSaved.setPrice(getPrice(value, vehicle.getCategoryVehicles(), ride.getStartDate(), ride.getEndDate()));

        rideSaved = rideRepository.save(rideSaved);

        rideLogRepository.save(new RideLog(rideSaved.getId(), rideSaved.getState()));

        return rideSaved;
    }

    public List<Ride> getAll(){

        return rideRepository.findAll();
    }

    public Ride update(Long id, RideDtoUpdateRequest ride) {

        Ride oldRide = rideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_RIDE));

        RidePointDto originRidePoint = ride.getOriginPoint();
        RidePointDto destinationRidePoint = ride.getDestinationPoint();

        Vehicle vehicle = vehicleRepository.findByDomain(ride.getVehicleDomain())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_VEHICLE));

        Point originPoint = pointRepository.getByLatAndLng(originRidePoint.getLat(), originRidePoint.getLng())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_POINT));

        Point destinationPoint = pointRepository.getByLatAndLng(destinationRidePoint.getLat(), destinationRidePoint.getLng())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_POINT));

        User user = userRepository.getByDni(ride.getUserDni())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

        Ride rideUpdate = new Ride(ride, vehicle, originPoint, destinationPoint, user);

        String value = ride.getTariffType().getValue(ride.getTariffType());

        rideUpdate.setPrice(getPrice(value, vehicle.getCategoryVehicles(), ride.getStartDate(), ride.getEndDate()));
        rideUpdate.setId(id);
        rideUpdate.setCode(oldRide.getCode());

        rideLogRepository.save(new RideLog(id, rideUpdate.getState()));

        return rideRepository.save(rideUpdate);
    }

    public Ride deleteRide(Long id){
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_RIDE));

        LocalDateTime noPayTime = ride.getStartDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().minusMinutes(30);

        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

        if (now.isBefore(noPayTime) || now.isEqual(noPayTime))
            ride.setPrice(0d);

        ride.setState(RideState.CANCELLED);
        rideLogRepository.save(new RideLog(id, RideState.CANCELLED));
        rideRepository.save(ride);

        return rideRepository.cancelRide(id);
    }

    private Double getPrice(String value, VehicleCategory category, Date startDate, Date endDate){

        Long diff = endDate.getTime() - startDate.getTime();

        if(value.equals("priceHour")){
            Long time = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);

            if(time == 0)
                time = 1L;

            return time * category.getPriceHour();
        }

        if(value.equals("priceDay")){
            Long time = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            if(time == 0)
                time = 1L;

            return time * category.getPriceDay();
        }

        if(value.equals("priceWeek")){
            Long time = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            if(time == 0)
                time = 1L;

            time = time/7;

            return time * category.getPriceWeek();
        }

        return 0d;
    }
}
