package net.avalith.carDriver.models.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.User;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserDtoRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The last name is required")
    private String lastName;

    @NotBlank(message = "The dni is required")
    private String dni;

    public UserDtoRequest(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
    }
}
