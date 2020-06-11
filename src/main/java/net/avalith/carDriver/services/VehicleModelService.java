package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.VehicleModels;
import net.avalith.carDriver.models.dtos.requests.VehicleDtoRequest;
import net.avalith.carDriver.models.dtos.requests.VehicleModelDtoRequest;
import net.avalith.carDriver.repositories.BrandRepository;
import net.avalith.carDriver.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_BRAND;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_POINT;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_VEHICLE;

@Service
public class VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public VehicleModels save(VehicleModelDtoRequest models){
        Brand brandSearch = brandRepository.findByName(models.getNameBrand())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BRAND));//todo crear la excepcion
        return  vehicleModelRepository.save(new VehicleModels(models,brandSearch));
    }
    public List<VehicleModels> getAll(){
        return vehicleModelRepository.getAllActive();
    }

    public VehicleModels update (VehicleModelDtoRequest model, String name){
        VehicleModels auxM = vehicleModelRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_VEHICLE));
        Brand brandSearch = brandRepository.findByName(model.getNameBrand())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BRAND));//todo crear la excepcion
        auxM.setBrand(brandSearch);
        auxM.setCantPlace(model.getCantPlace());
        auxM.setIsAutomatic(model.getIsAutomatic());
        auxM.setName(model.getName());
        return vehicleModelRepository.save(auxM);
    }

    public void delete(String nameModel){
        if(vehicleModelRepository.delete(nameModel) < 1)
            throw new NotFoundException(NOT_FOUND_VEHICLE);
    }
}
