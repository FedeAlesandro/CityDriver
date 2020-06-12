package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.responses.DeleteResponseDto;
import net.avalith.carDriver.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

import static net.avalith.carDriver.utils.Constants.DELETED_PROVIDER;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/")
    public ResponseEntity<List<Provider>>getAll(){
        List<Provider> listProviders = providerService.getAll();
        if (listProviders.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listProviders);
    }

    @PostMapping("/")
    public ResponseEntity<Provider> save(@RequestBody @Valid Provider provider){

        return ResponseEntity.status(HttpStatus.CREATED).body(providerService.save(provider));
    }

    @PutMapping("/{name}")
    public Provider update(@PathVariable("name") String name, @RequestBody Provider provider){
        return providerService.update(name, provider);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<DeleteResponseDto> delete(@PathVariable("name") String name){
       providerService.deleteProvider(name);
       return ResponseEntity.ok(new DeleteResponseDto(String.format(DELETED_PROVIDER, name)));
    }

}
