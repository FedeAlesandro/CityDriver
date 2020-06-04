package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Provider;
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


import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService pr;

    @GetMapping("/")
    public ResponseEntity<List<Provider>>getAll(){
        List<Provider> listProviders = pr.getAll();
        if (listProviders.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listProviders);
    }

    @PostMapping("/")
    public ResponseEntity<Provider> save(@RequestBody Provider provider){
        return ResponseEntity.status(HttpStatus.CREATED).body(pr.save(provider));
    }

    @PutMapping("/{id}")
    public Provider update(@PathVariable("id") Long id, @RequestBody Provider provider){
        return pr.update(id, provider);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
       pr.deleteProvider(id);
       return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
