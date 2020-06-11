package net.avalith.carDriver.models.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDtoRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The last name is required")
    private String lastName;

    @NotNull(message = "The birth date is required")
    private Date birthDate;

    @NotBlank(message = "The dni is required")
    private String dni;
}
