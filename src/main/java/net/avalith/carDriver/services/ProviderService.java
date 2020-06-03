package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Providers;
import net.avalith.carDriver.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Providers save(Providers providers){
       return providerRepository.save(providers);
    }

    public Optional<Providers> findById(Long id){
        return this.providerRepository.findById(id);
    }
    public List<Providers> getall(){
        return providerRepository.findAll();
    }

    public Boolean deleteProvider(Long id){
        Boolean rpta = false;
        if(!providerRepository.findById(id).isPresent()){
            providerRepository.deleteById(id);
            rpta = true;
        }
        return rpta;
    }

    public  Providers update(Providers providers){
        Providers providers1;
        providers1 = providerRepository.findByName(providers.getName());
        if (providers1==null){
            providers1.setName(providers.getName());
            providerRepository.save(providers1);
        }
        return providers1;
    }
}
