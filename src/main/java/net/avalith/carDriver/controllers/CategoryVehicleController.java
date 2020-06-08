package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.CategoryVehicles;
import net.avalith.carDriver.services.CategoryVehicleService;
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
public class CategoryVehicleController {

    @Autowired
    private CategoryVehicleService categoryVehicleService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryVehicles>> getAll(){
        List<CategoryVehicles> listCategoryVehicles = categoryVehicleService.getAll();
        if (listCategoryVehicles.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listCategoryVehicles);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryVehicles> save(@RequestBody CategoryVehicles categoryVehicle){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryVehicleService.save(categoryVehicle));
    }
}
