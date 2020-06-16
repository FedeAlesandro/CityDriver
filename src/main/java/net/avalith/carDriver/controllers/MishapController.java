package net.avalith.carDriver.controllers;


import net.avalith.carDriver.models.Mishap;
import net.avalith.carDriver.services.MishapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mishaps")
public class MishapController {

    @Autowired
    private MishapService mishapService;

    @GetMapping("/")
    public ResponseEntity<List<Mishap>> getAll(){
        List<Mishap> listMishaps = mishapService.getAll();
        if (listMishaps.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listMishaps);
    }
    @PostMapping("/ride/{idRide}")
    public ResponseEntity<Mishap> save(@RequestBody Mishap mishap, @PathVariable("idRide") Long idRide){
        return ResponseEntity.ok(mishapService.save(mishap, idRide));
    }
}
