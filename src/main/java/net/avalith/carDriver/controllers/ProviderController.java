package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.requests.ProviderDtoRequest;
import net.avalith.carDriver.models.dtos.responses.DeleteResponseDto;
import net.avalith.carDriver.models.dtos.responses.ProviderDtoResponse;
import net.avalith.carDriver.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static net.avalith.carDriver.utils.Constants.DELETED_PROVIDER;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

//    @Value("${api.key.name}")
//    private String keyName;

    @GetMapping("/")
    public ResponseEntity<List<ProviderDtoResponse>>getAll(@RequestHeader(value = "API_KEY", required = false) String apiKey){
        /*if(apiKey == null || !apiKey.equals(api_key))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();*/

        List<Provider> listProviders = providerService.getAll();
        if (listProviders.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listProviders.stream()
                .map(ProviderDtoResponse::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<ProviderDtoResponse> save(@RequestHeader(value = "API_KEY", required = false) String apiKey, @RequestBody @Valid ProviderDtoRequest provider){
       /* if(apiKey == null || !apiKey.equals(api_key))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();*/

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProviderDtoResponse(providerService.save(provider)));
    }

    @PutMapping("/{name}")
    public ResponseEntity<ProviderDtoResponse> update(@RequestHeader(value = "API_KEY", required = false) String apiKey, @PathVariable("name") String name, @RequestBody ProviderDtoRequest provider){
        /*if(apiKey == null || !apiKey.equals(api_key))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();*/

        return ResponseEntity.ok(new ProviderDtoResponse(providerService.update(name, provider)));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<DeleteResponseDto> delete(@RequestHeader(value = "API_KEY", required = false) String apiKey, @PathVariable("name") String name){
       /* if(apiKey == null || !apiKey.equals(api_key))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();*/
        providerService.deleteProvider(name);

        return ResponseEntity.ok(new DeleteResponseDto(String.format(DELETED_PROVIDER, name)));
    }

}
