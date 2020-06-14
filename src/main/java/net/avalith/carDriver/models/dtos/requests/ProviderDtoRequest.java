package net.avalith.carDriver.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDtoRequest {

    @NotBlank(message = "The name is required")
    private String name;

    private Boolean isActive;

    public ProviderDtoRequest(String name) {
        this.name = name;
        this.isActive = Boolean.TRUE;
    }
}
