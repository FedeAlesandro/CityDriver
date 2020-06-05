package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public Point save(Point point){
        return pointRepository.save(point);
    }

    public List<Point> getAll(){
        return pointRepository.findAll();
    }
}
