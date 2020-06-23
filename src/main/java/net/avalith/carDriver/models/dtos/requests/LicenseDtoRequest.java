package net.avalith.carDriver.models.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.License;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LicenseDtoRequest {

    @NotBlank(message = "The license number is required")
    private String number;

    public LicenseDtoRequest(License license) {
        number = license.getNumber();
    }

    public LicenseDtoRequest(String number) {
        this.number = number;
    }
}
