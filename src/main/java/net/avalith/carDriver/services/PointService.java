package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.models.dtos.requests.PointDtoRequest;
import net.avalith.carDriver.repositories.CityRepository;
import net.avalith.carDriver.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_CITY;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_POINT;
import static net.avalith.carDriver.utils.Constants.POINT_ALREADY_EXISTS;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private CityRepository cityRepository;

    public Point save(PointDtoRequest point){

        City city = cityRepository.findByName(point.getCityName())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_CITY));

        if(pointRepository
                .findByCoordinateLatitudeAndCoordinateLongitude(point.getCoordinateLatitude(), point.getCoordinateLongitude())
                .isPresent())
            throw new AlreadyExistsException(POINT_ALREADY_EXISTS);

        return pointRepository.save(Point.fromPointDtoRequest(point, city));
    }

    public List<Point> getAll(){
        return pointRepository.findAll();
    }
}
