package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.dtos.requests.BrandDtoRequest;
import net.avalith.carDriver.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_BRAND;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_VEHICLE_MODEL;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand save(BrandDtoRequest brand){
        return brandRepository.save(new Brand(brand));
    }

    public List<Brand> getAll(){
        return brandRepository.getAllActive();
    }

    public void deleteBrand(String name){
        if(brandRepository.delete(name)<1){
            throw new NotFoundException(NOT_FOUND_BRAND);
        }
    }
    public Brand getById(Long id){
         return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BRAND));
    }
    public Brand update(String name, Brand brand){
        Brand brand1 = brandRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BRAND));
        brand1.setName(brand.getName());
        return  brandRepository.save(brand1);
    }
}
