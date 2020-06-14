package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.requests.ProviderDtoRequest;
import net.avalith.carDriver.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_PROVIDER;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_VEHICLE_CATEGORY;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_VEHICLE_MODEL;
import static net.avalith.carDriver.utils.Constants.PROVIDER_ALREADY_EXISTS;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public void deleteProvider(String name){
        if(providerRepository.delete(name) < 1)
            throw new NotFoundException(NOT_FOUND_PROVIDER);
    }

    public Provider update(String name, Provider provider){
        Provider provider1 = providerRepository.findByName(name)
            .orElseThrow(()-> new NotFoundException(NOT_FOUND_PROVIDER));

        if(providerRepository.findByName(provider.getName()).isPresent())
            throw new AlreadyExistsException(PROVIDER_ALREADY_EXISTS);

        provider1.setName(provider.getName());

        return  providerRepository.save(provider1);
    }
    public List<Provider> getAll(){
        return providerRepository.getAllActive();
    }
    public Provider save(ProviderDtoRequest provider){

        if(providerRepository.findByName(provider.getName()).isPresent())
            throw new AlreadyExistsException(PROVIDER_ALREADY_EXISTS);

        return providerRepository.save(new Provider(provider));
    }
}
