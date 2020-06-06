package net.avalith.carDriver.services;

import net.avalith.carDriver.models.CategoryVehicles;
import net.avalith.carDriver.models.dtos.requests.VehicleDTO;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.VehicleModels;
import net.avalith.carDriver.repositories.CategoryVehicleRepository;
import net.avalith.carDriver.repositories.ProviderRepository;
import net.avalith.carDriver.repositories.VehicleModelRepository;
import net.avalith.carDriver.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    public CategoryVehicleRepository categoryVehicleRepository;

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle save(VehicleDTO vehicleDTO){

        Provider providerSearch = providerRepository.findByName(vehicleDTO.getNameProvider())
                .orElseThrow(RuntimeException::new);//todo
        VehicleModels modelSearch = vehicleModelRepository.findByName(vehicleDTO.getNameModel())
                .orElseThrow(RuntimeException::new);//todo
        CategoryVehicles categorySearch = categoryVehicleRepository.findByName(vehicleDTO.getNameCategory())
                .orElseThrow(RuntimeException::new);
        Vehicle vehicle = Vehicle.vehicleFromVehicleDTO(vehicleDTO, providerSearch, modelSearch, categorySearch);

         return  vehicleRepository.save(vehicle);
    }

}
