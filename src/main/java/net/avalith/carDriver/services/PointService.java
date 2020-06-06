package net.avalith.carDriver.services;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.models.dtos.PointDto;
import net.avalith.carDriver.repositories.CityRepository;
import net.avalith.carDriver.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private CityRepository cityRepository;

    public Point save(PointDto point){
        City city = cityRepository.findByName(point.getCityName())
                .orElseThrow(RuntimeException::new); //todo excepcion custom
        return pointRepository.save(Point.fromPointDto(point, city));
    }

    public List<Point> getAll(){
        return pointRepository.findAll();
    }
}
