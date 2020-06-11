package net.avalith.carDriver.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.User;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDtoNoLicense {

    private String name;

    private String lastName;

    private String dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    public UserDtoNoLicense(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
        this.birthDate = user.getBirthDate();
    }

}
