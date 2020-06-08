package net.avalith.carDriver.services;

import net.avalith.carDriver.models.VehicleCategory;
import net.avalith.carDriver.repositories.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCategoryService {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    public List<VehicleCategory> getAll(){
        return vehicleCategoryRepository.findAll();
    }

    public VehicleCategory save(VehicleCategory vehicleCategory){
        return vehicleCategoryRepository.save(vehicleCategory);
    }
}
