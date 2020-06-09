package net.avalith.carDriver.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.User;

@Data
@NoArgsConstructor
public class UserDtoNoLicense {

    private String name;

    private String lastName;

    private String dni;

    public UserDtoNoLicense(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
    }

}
