package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.User;

@Data
@NoArgsConstructor
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
        if(user.getLicense()!=null){
            this.licenseNumber = user.getLicense().getNumber();
            this.validated = user.getLicense().getValidated();
        }
    }
}
