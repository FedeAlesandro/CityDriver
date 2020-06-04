package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.services.BrandService;
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
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAll(){
        List<Brand> listBrands = brandService.getAll();
        if (listBrands.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(listBrands);
    }
    @PostMapping("/")
    public ResponseEntity<Brand> save(@RequestBody Brand brand){
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.save(brand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> update(@PathVariable("id") Long id, @RequestBody Brand brand){
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.update(id,brand));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        brandService.deleteBrand(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
