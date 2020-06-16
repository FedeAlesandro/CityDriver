package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.dtos.requests.BrandDtoRequest;
import net.avalith.carDriver.models.dtos.responses.BrandDtoResponse;
import net.avalith.carDriver.models.dtos.responses.DeleteResponseDto;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static net.avalith.carDriver.utils.Constants.DELETED_BRAND;
import static net.avalith.carDriver.utils.Constants.DELETED_POINT;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<List<BrandDtoResponse>> getAll() {
        List<Brand> listBrands = brandService.getAll();
        if (listBrands.isEmpty()) {

            return ResponseEntity.noContent().build();
        } else{
            List<BrandDtoResponse> listBrandsResponse = listBrands.stream()
                    .map(BrandDtoResponse::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(listBrandsResponse);
        }
    }
    @PostMapping("/")
    public ResponseEntity<BrandDtoResponse> save(@RequestBody @Valid BrandDtoRequest brand){

        return ResponseEntity.ok(new BrandDtoResponse(brandService.save(brand)));
    }

    @PutMapping("/{name}")
    public ResponseEntity<BrandDtoResponse> update(@PathVariable("name") String name, @RequestBody @Valid Brand brand){

        return ResponseEntity.status(HttpStatus.CREATED).body(new BrandDtoResponse(brandService.update(name, brand)));

    }
}
