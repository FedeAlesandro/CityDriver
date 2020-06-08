package net.avalith.carDriver.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.dtos.requests.UserDtoRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dni", unique = true)
    private String dni;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @OneToOne
    @JoinColumn(name = "id_license", referencedColumnName = "id_license")
    private License license;

    public static User userFromUserDtoRequest(UserDtoRequest userDto){
        return User.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .dni(userDto.getDni())
                .build();
    }
}
