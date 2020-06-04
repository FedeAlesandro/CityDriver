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

    public void deleteProvider(Long id){
        providerRepository.findById(id)
                .orElseThrow(RuntimeException::new);//toDo create exception custom
        providerRepository.deleteById(id);
    }

    public Providers update(Long id, Providers providers){
        Providers providers1= providerRepository.findById(id)
            .orElseThrow(RuntimeException::new);// toDo create exception custom
        providers1.setName(providers.getName());
        return  providerRepository.save(providers1);
    }
    public List<Providers> getAll(){
        return providerRepository.findAll();
    }
    public Providers save(Providers providers){
        return providerRepository.save(providers);
    }




}
