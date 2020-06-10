package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.License;

import java.util.Date;

@Data
@NoArgsConstructor
public class LicenseDtoResponse {

    private String number;

    private Date expirationDate;

    private Boolean validated;

    public LicenseDtoResponse(License license) {
        number = license.getNumber();
        expirationDate = license.getExpirationDate();
        validated = license.getValidated();
    }
}
