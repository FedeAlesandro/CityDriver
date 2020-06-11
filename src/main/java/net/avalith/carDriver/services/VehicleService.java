package net.avalith.carDriver.services;

import net.avalith.carDriver.models.CategoryVehicles;
import net.avalith.carDriver.models.dtos.requests.VehicleDtoRequest;
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

    public Vehicle save(VehicleDtoRequest vehicleDtoRequest){

        Provider providerSearch = providerRepository.findByName(vehicleDtoRequest.getNameProvider())
                .orElseThrow(RuntimeException::new);//todo
        VehicleModels modelSearch = vehicleModelRepository.findByName(vehicleDtoRequest.getNameModel())
                .orElseThrow(RuntimeException::new);//todo
        CategoryVehicles categorySearch = categoryVehicleRepository.findByName(vehicleDtoRequest.getNameCategory())
                .orElseThrow(RuntimeException::new);
        Vehicle vehicle = Vehicle.vehicleFromVehicleDTO(vehicleDtoRequest, providerSearch, modelSearch, categorySearch);

         return  vehicleRepository.save(vehicle);
    }

}
