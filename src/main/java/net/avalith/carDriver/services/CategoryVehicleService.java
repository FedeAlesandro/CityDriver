package net.avalith.carDriver.services;

import net.avalith.carDriver.models.CategoryVehicles;
import net.avalith.carDriver.repositories.CategoryVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryVehicleService {

    @Autowired
    private CategoryVehicleRepository categoryVehicleRepository;

    public List<CategoryVehicles> getAll(){
        return categoryVehicleRepository.findAll();
    }

    public CategoryVehicles save(CategoryVehicles categoryVehicles){
        return categoryVehicleRepository.save(categoryVehicles);
    }
}
