package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.dtos.requests.BrandDtoRequest;
import net.avalith.carDriver.repositories.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.avalith.carDriver.utils.Constants.BRAND_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_BRAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    private BrandService brandService;

    @Mock
    private BrandRepository mockBrandRepository;

    @BeforeEach
    public void config(){
        brandService = new BrandService(mockBrandRepository);
    }

    @Test
    public void getAllBrands(){
        List<Brand> brands = new ArrayList<>();
        when(mockBrandRepository.getAllActive()).thenReturn(brands);
        assertNotNull(brandService.getAll());
    }

    @Test
    public void save(){
        BrandDtoRequest brandDto = new BrandDtoRequest("name",Boolean.TRUE);
        Brand brand = new Brand(brandDto);

        when(mockBrandRepository.findByName(brandDto.getName())).thenReturn(Optional.empty());
        when(mockBrandRepository.save(brand)).thenReturn(brand);

        assertEquals(brand, brandService.save(brandDto));
    }

    @Test
    public void saveAlreadyExistsException(){
        BrandDtoRequest brandDto = new BrandDtoRequest("name",Boolean.TRUE);

        when(mockBrandRepository.findByName(brandDto.getName())).thenReturn(Optional.of(new Brand()));

        AlreadyExistsException ex = Assertions.assertThrows(AlreadyExistsException.class, () -> brandService.save(brandDto));
        Assertions.assertEquals(ex, new AlreadyExistsException(BRAND_ALREADY_EXISTS));
    }

    @Test
    public void update(){
        BrandDtoRequest brandDtoRequest = new BrandDtoRequest("chevrolet", Boolean.TRUE);
        Brand brand = new Brand("toyota");
        Brand auxBrand = new Brand("chevrolet");

        when(mockBrandRepository.findByName("toyota")).thenReturn(Optional.of(brand));
        when(mockBrandRepository.save(brand)).thenReturn(auxBrand);

        assertEquals(auxBrand, brandService.update("toyota", brandDtoRequest));
    }

    @Test
    public void updateNotFoundName(){
        BrandDtoRequest brandDtoRequest = new BrandDtoRequest("toyota", Boolean.TRUE);

        when(mockBrandRepository.findByName("toyota")).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> brandService.update("toyota",brandDtoRequest));
        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_BRAND));

    }
}

