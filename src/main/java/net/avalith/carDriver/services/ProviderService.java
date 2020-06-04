package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public void deleteProvider(Long id){
        providerRepository.findById(id)
                .orElseThrow(RuntimeException::new);//toDo create exception custom
        providerRepository.deleteById(id);
    }

    public Provider update(Long id, Provider provider){
        Provider provider1 = providerRepository.findById(id)
            .orElseThrow(RuntimeException::new);// toDo create exception custom
        provider1.setName(provider.getName());
        return  providerRepository.save(provider1);
    }
    public List<Provider> getAll(){
        return providerRepository.findAll();
    }
    public Provider save(Provider provider){
        return providerRepository.save(provider);
    }




}
