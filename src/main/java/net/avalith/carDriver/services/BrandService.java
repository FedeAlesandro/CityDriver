package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.requests.BrandDtoRequest;
import net.avalith.carDriver.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Brand auxBrand = brandRepository.findByName(name)
                .orElseThrow(RuntimeException::new);//toDo create exception custom
        auxBrand.setIsActive(Boolean.FALSE);
        brandRepository.save(auxBrand);
    }
    public Brand getById(Long id){
         return brandRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    public Brand update(String name, Brand brand){
        Brand brand1 = brandRepository.findByName(name)
                .orElseThrow(RuntimeException::new);// toDo create exception custom
        brand1.setName(brand.getName());
        return  brandRepository.save(brand1);
    }
}
