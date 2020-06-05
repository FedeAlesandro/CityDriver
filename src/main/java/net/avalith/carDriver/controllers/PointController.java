package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/")
    public ResponseEntity<Point> save(@RequestBody Point point){
        return ResponseEntity.ok(pointService.save(point));
    }

    @GetMapping("/")
    public ResponseEntity<List<Point>> getAll(){
        List<Point> points = pointService.getAll();
        if (points.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(points);
    }
}
