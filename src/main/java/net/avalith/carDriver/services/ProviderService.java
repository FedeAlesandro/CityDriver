package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.InvalidRequestException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.requests.ProviderDtoRequest;
import net.avalith.carDriver.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_PROVIDER;
import static net.avalith.carDriver.utils.Constants.PROVIDER_ALREADY_EXISTS;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public void deleteProvider(String name){
        if(providerRepository.delete(name.replace("-"," ")) < 1)
            throw new NotFoundException(NOT_FOUND_PROVIDER);
    }

    public Provider update(String name, ProviderDtoRequest provider){
        Provider provider1 = providerRepository.findByName(name.replace("-"," "))
            .orElseThrow(()-> new NotFoundException(NOT_FOUND_PROVIDER));

        return  providerRepository.save(Provider.fromDtoRequest(provider1, provider));
    }
    public List<Provider> getAll(){
        
      return providerRepository.getAllActive();
    }
    public Provider save(ProviderDtoRequest provider){

        if(providerRepository.findByName(provider.getName()).isPresent())
            throw new AlreadyExistsException(PROVIDER_ALREADY_EXISTS);

        Provider auxProvider = providerRepository.findNotAvailableByName(provider.getName());

        if(auxProvider != null){
            if ((auxProvider.getName().equals(provider.getName())) && (auxProvider.getPassword().equals(provider.getPassword()))){
                auxProvider.setPhone(provider.getPhone());
                auxProvider.setEmail(provider.getEmail());
                auxProvider.setBusinessName(provider.getBusinessName());
                auxProvider.setIsActive(Boolean.TRUE);
                return providerRepository.save(auxProvider);
            }
        }
      
        return providerRepository.save(new Provider(provider));
    }
}
