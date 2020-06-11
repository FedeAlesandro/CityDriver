package net.avalith.carDriver.models.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDtoUpdateRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The last name is required")
    private String lastName;

    @NotNull(message = "The birth date is required")
    private Date birthDate;
}
