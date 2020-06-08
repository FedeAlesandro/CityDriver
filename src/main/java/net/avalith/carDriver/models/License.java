package net.avalith.carDriver.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.models.dtos.requests.LicenseDtoRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "licenses")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class License {

    @Id
    @Column(name = "id_license")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @JsonFormat(timezone = "GMT-03:00", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "validated")
    private Boolean validated;

    public static License fromLicenseDtoRequest(LicenseDtoRequest license){
        return License.builder()
                .number(license.getNumber())
                .build();
    }
}
