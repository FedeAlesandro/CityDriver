package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.VehicleModels;
import net.avalith.carDriver.repositories.BrandRepository;
import net.avalith.carDriver.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public VehicleModels save(VehicleModels models, String brand){
        Brand brandSearch = brandRepository.findByName(brand)
                .orElseThrow(RuntimeException::new);//todo crear la excepcion
        models.setBrand(brandSearch);
        return  vehicleModelRepository.save(models);
    }
    public List<VehicleModels> getAll(){
        return vehicleModelRepository.findAll();
    }
}
