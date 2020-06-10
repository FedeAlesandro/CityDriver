package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.dtos.requests.PointDtoUpdateRequest;
import net.avalith.carDriver.models.dtos.requests.RideDtoRequest;
import net.avalith.carDriver.models.dtos.requests.RideDtoUpdateRequest;
import net.avalith.carDriver.models.dtos.responses.PointDtoResponse;
import net.avalith.carDriver.models.dtos.responses.RideDtoResponse;
import net.avalith.carDriver.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/")
    public ResponseEntity<RideDtoResponse> save(@RequestBody @Valid RideDtoRequest ride){
        return ResponseEntity.status(HttpStatus.CREATED).body(new RideDtoResponse(rideService.save(ride)));
    }

    @GetMapping("/")
    public ResponseEntity<List<RideDtoResponse>> getAll(){
        List<Ride>rides = rideService.getAll();
        if (rides.isEmpty())
            return ResponseEntity.noContent().build();
        else{
            List<RideDtoResponse>rideResponses = rides.stream()
                    .map(RideDtoResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(rideResponses);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<RideDtoResponse> update(@RequestParam(value = "id") Long id,
                                                   @RequestBody @Valid RideDtoUpdateRequest ride){
        return ResponseEntity.ok(new RideDtoResponse(rideService.update(id, ride)));
    }
}
