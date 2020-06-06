package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.models.dtos.PointDto;
import net.avalith.carDriver.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/points")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/")
    public ResponseEntity<PointDto> save(@RequestBody PointDto point){
        Point pointAux = pointService.save(point);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PointDto(pointAux));
    }

    @GetMapping("/")
    public ResponseEntity<List<PointDto>> getAll(){
        List<Point> pointsAux = pointService.getAll();
        if (pointsAux.isEmpty())
            return ResponseEntity.noContent().build();
        else
        {
            List<PointDto> points = pointsAux.stream()
                    .map(PointDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(points);
        }
    }
}
