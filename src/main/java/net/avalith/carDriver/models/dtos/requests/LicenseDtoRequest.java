package net.avalith.carDriver.models.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.License;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LicenseDtoRequest {

    @NotBlank(message = "The license number is required")
    private String number;

    public LicenseDtoRequest(License license) {
        number = license.getNumber();
    }
}
