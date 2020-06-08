package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.VehicleCategory;
import net.avalith.carDriver.services.VehicleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category-vehicles")
@RestController
public class VehicleCategoryController {

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

    @GetMapping("/")
    public ResponseEntity<List<VehicleCategory>> getAll(){
        List<VehicleCategory> listVehicleCategoryVehicles = vehicleCategoryService.getAll();
        if (listVehicleCategoryVehicles.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listVehicleCategoryVehicles);
    }

    @PostMapping("/")
    public ResponseEntity<VehicleCategory> save(@RequestBody VehicleCategory categoryVehicle){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleCategoryService.save(categoryVehicle));
    }
}
