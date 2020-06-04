package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand save(Brand brand){
        return brandRepository.save(brand);
    }
    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    public void deleteBrand(Long id){
        brandRepository.findById(id)
                .orElseThrow(RuntimeException::new);//toDo create exception custom
        brandRepository.deleteById(id);
    }
    public Brand getById(Long id){
         return brandRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    public Brand update(Long id, Brand brand){
        Brand brand1 = brandRepository.findById(id)
                .orElseThrow(RuntimeException::new);// toDo create exception custom
        brand1.setName(brand.getName());
        return  brandRepository.save(brand1);
    }
}
