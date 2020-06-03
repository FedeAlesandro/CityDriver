package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Providers;
import net.avalith.carDriver.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService pr;

    @GetMapping("/")
    public List<Providers> getAll(){
        return pr.getall();
    }

    @PostMapping("/")
    public Providers save(@RequestBody Providers providers){
        return pr.save(providers);
    }

    @PutMapping("/prodivers/{id}")
    public Providers update(@PathVariable("id") Long id, Providers providers){
        return pr.update(providers);
    }

}
