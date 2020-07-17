package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.VehicleCategory;
import net.avalith.carDriver.models.dtos.requests.VehicleCategoryDtoRequest;
import net.avalith.carDriver.models.enums.VehicleCategoryEnum;
import net.avalith.carDriver.repositories.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_VEHICLE_CATEGORY;

@Service
public class VehicleCategoryService {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    public List<VehicleCategory> getAll(){
        return vehicleCategoryRepository.getAllActive();
    }

    public VehicleCategory save(VehicleCategoryDtoRequest vehicleCategory){

        return vehicleCategoryRepository.save(new VehicleCategory(vehicleCategory));
    }
    public void delete(String name){
        if(vehicleCategoryRepository.delete(name)<1){
            throw new NotFoundException(NOT_FOUND_VEHICLE_CATEGORY);
        }
    }

    public VehicleCategory update(VehicleCategoryDtoRequest vehicleCategoryDtoRequest, VehicleCategoryEnum name){
        VehicleCategory auxVehi = vehicleCategoryRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException(NOT_FOUND_VEHICLE_CATEGORY));

        auxVehi.setName(vehicleCategoryDtoRequest.getName());
        auxVehi.setPriceHour(vehicleCategoryDtoRequest.getPriceHour());
        auxVehi.setPriceDay(vehicleCategoryDtoRequest.getPriceDay());
        auxVehi.setPriceWeek(vehicleCategoryDtoRequest.getPriceWeek());

        return vehicleCategoryRepository.save(auxVehi);
    }
}
