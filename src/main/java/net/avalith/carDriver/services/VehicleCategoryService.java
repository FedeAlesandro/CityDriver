package net.avalith.carDriver.services;

import net.avalith.carDriver.models.CategoryVehicles;
import net.avalith.carDriver.repositories.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCategoryService {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    public List<CategoryVehicles> getAll(){
        return vehicleCategoryRepository.findAll();
    }

    public CategoryVehicles save(CategoryVehicles categoryVehicles){
        return vehicleCategoryRepository.save(categoryVehicles);
    }
}
