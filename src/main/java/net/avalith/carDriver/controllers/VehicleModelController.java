package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.VehicleModels;
import net.avalith.carDriver.models.dtos.requests.VehicleModelDtoRequest;
import net.avalith.carDriver.models.dtos.responses.DeleteResponseDto;
import net.avalith.carDriver.models.dtos.responses.VehicleModelDtoResponse;
import net.avalith.carDriver.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static net.avalith.carDriver.utils.Constants.DELETED_VEHICLE_MODEL;

@RequestMapping("/vehicle-models")
@RestController
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping("/")
    public ResponseEntity<List<VehicleModelDtoResponse>> getAll(){
        List<VehicleModels> listVehicleModels = vehicleModelService.getAll();
        if (listVehicleModels.isEmpty()){

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listVehicleModels.stream()
                .map(VehicleModelDtoResponse::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<VehicleModelDtoResponse> save(@RequestBody @Valid VehicleModelDtoRequest vehicle){

        return ResponseEntity.status(HttpStatus.CREATED).body(new VehicleModelDtoResponse(vehicleModelService.save(vehicle)));
    }

    @PutMapping("/{name}")
    public ResponseEntity<VehicleModelDtoResponse> update(@PathVariable("name") String name, @RequestBody @Valid VehicleModelDtoRequest vehicleModel){

        return ResponseEntity.ok(new VehicleModelDtoResponse(vehicleModelService.update(vehicleModel,name)));
    }
}
