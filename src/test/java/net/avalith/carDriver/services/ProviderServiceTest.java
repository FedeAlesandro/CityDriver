package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.requests.ProviderDtoRequest;
import net.avalith.carDriver.repositories.ProviderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.avalith.carDriver.utils.Constants.BRAND_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.PROVIDER_ALREADY_EXISTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProviderServiceTest {

    private ProviderService providerService;

    @Mock
    private ProviderRepository mockProviderRepository;

    @BeforeEach
    public void config(){
        providerService = new ProviderService(mockProviderRepository);
    }

    @Test
    public void getAllProvider(){
        List<Provider> providers = new ArrayList<>();
        when(mockProviderRepository.getAllActive()).thenReturn(providers);
        assertNotNull(providerService.getAll());
    }

    @Test
    public void save(){
        ProviderDtoRequest providerDtoRequest = new ProviderDtoRequest(
                                                    "Car One", "car@hotmail.com","car S.A.",
                                                    "457812","123456");
        Provider provider = new Provider(providerDtoRequest);

        when(mockProviderRepository.findByName(providerDtoRequest.getName())).thenReturn(Optional.empty());
        when(mockProviderRepository.findNotAvailableByName(providerDtoRequest.getName())).thenReturn(null);
        when(mockProviderRepository.save(new Provider(providerDtoRequest))).thenReturn(provider);

        assertEquals(provider,providerService.save(providerDtoRequest));
    }

    @Test
    public void saveExistProvider(){
        ProviderDtoRequest providerDtoRequest = new ProviderDtoRequest(
                "Car One", "car@hotmail.com","car S.A.",
                "457812","123456");
        Provider auxProvider = new Provider(providerDtoRequest);

        when(mockProviderRepository.findByName(providerDtoRequest.getName())).thenReturn(Optional.empty());

        when(mockProviderRepository.findNotAvailableByName(providerDtoRequest.getName())).thenReturn(auxProvider);

        when(mockProviderRepository.save(auxProvider)).thenReturn(auxProvider);

        assertEquals(auxProvider, providerService.save(providerDtoRequest));
    }

    @Test
    public void saveExistNameProvider(){
        ProviderDtoRequest providerDtoRequest = new ProviderDtoRequest(
                "Car One", "car@hotmail.com","car S.A.",
                "457812","123456");

        when(mockProviderRepository.findByName(providerDtoRequest.getName())).thenReturn(Optional.of(new Provider()));

        AlreadyExistsException ex = Assertions.assertThrows(AlreadyExistsException.class, () -> providerService.save(providerDtoRequest));
        Assertions.assertEquals(ex, new AlreadyExistsException(PROVIDER_ALREADY_EXISTS));
    }


}
