package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.License;
import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.dtos.requests.LicenseDtoRequest;
import net.avalith.carDriver.repositories.LicenseRepository;
import net.avalith.carDriver.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.avalith.carDriver.utils.Constants.LICENSE_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_LICENSE;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_LICENSE_USER;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LicenseServiceTest {

    LicenseService licenseService;

    @Mock
    LicenseRepository licenseRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        licenseService = new LicenseService(licenseRepository, userRepository);
    }

    @Test
    public void getAllTest(){
        List<License> licenses = new ArrayList<>();
        when(licenseRepository.findAll()).thenReturn(licenses);
        Assertions.assertNotNull(licenseService.getAll());
    }

    @Test
    public void saveAlreadyExistsException(){
        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.of(new License()));

        AlreadyExistsException ex = Assertions.assertThrows(AlreadyExistsException.class, () -> licenseService.save(new LicenseDtoRequest("42454677")));
        Assertions.assertEquals(ex, new AlreadyExistsException(LICENSE_ALREADY_EXISTS));
    }

    @Test
    public void saveUserNotFoundException(){
        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.empty());
        when(userRepository.getByDni("42454677")).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> licenseService.save(new LicenseDtoRequest("42454677")));
        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_LICENSE_USER));
    }

    @Test
    public void saveTest(){
        License license = new License("42454677");

        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.empty());
        when(userRepository.getByDni("42454677")).thenReturn(Optional.of(new User()));
        when(licenseRepository.save(new License("42454677"))).thenReturn(license);

        Assertions.assertEquals(license, licenseService.save(new LicenseDtoRequest("42454677")));
    }

    @Test
    public void updateNotFoundException(){
        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> licenseService.update("42454677", new LicenseDtoRequest("42454677")));
        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_LICENSE));
    }

    @Test
    public void UpdateAlreadyExistsException(){
        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.of(new License()));
        when(licenseRepository.findByNumber("42454700")).thenReturn(Optional.of(new License()));

        AlreadyExistsException ex = Assertions.assertThrows(AlreadyExistsException.class, () -> licenseService.update("42454677", new LicenseDtoRequest("42454700")));
        Assertions.assertEquals(ex, new AlreadyExistsException(LICENSE_ALREADY_EXISTS));
    }

    @Test
    public void UpdateUserNotFoundException(){
        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.of(new License()));
        when(licenseRepository.findByNumber("42454700")).thenReturn(Optional.empty());
        when(userRepository.getByDni("42454700")).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> licenseService.update("42454677", new LicenseDtoRequest("42454700")));
        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_LICENSE_USER));
    }

    @Test
    public void UpdateTest(){
        License license = new License("42454700");

        when(licenseRepository.findByNumber("42454677")).thenReturn(Optional.of(new License()));
        when(licenseRepository.findByNumber("42454700")).thenReturn(Optional.empty());
        when(userRepository.getByDni("42454700")).thenReturn(Optional.of(new User()));
        when(licenseRepository.save(new License("42454700"))).thenReturn(license);

        Assertions.assertEquals(license, licenseService.update("42454677", new LicenseDtoRequest(license)));
    }
}
