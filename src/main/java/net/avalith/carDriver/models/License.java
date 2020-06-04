package net.avalith.carDriver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "licenses")
@NoArgsConstructor
@AllArgsConstructor
public class License {

    @Id
    @Column(name = "id_license")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "validated")
    private Boolean validated;

}
