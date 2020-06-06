package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import net.avalith.carDriver.models.User;

@Data
public class UserDtoResponse {

    private String name;

    private String lastName;

    private String dni;

    private String licenseNumber;

    private Boolean validated;

    public UserDtoResponse(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
        this.licenseNumber = user.getLicense().getNumber();
        this.validated = user.getLicense().getValidated();
    }
}
