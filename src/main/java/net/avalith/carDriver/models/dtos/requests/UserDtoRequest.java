package net.avalith.carDriver.models.dtos.requests;

import lombok.Data;
import net.avalith.carDriver.models.User;

@Data
public class UserDtoRequest {

    private String name;

    private String lastName;

    private String dni;

    private String licenseNumber;

    public UserDtoRequest(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
        this.licenseNumber = user.getLicense().getNumber();
    }
}
