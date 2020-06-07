package net.avalith.carDriver.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_models")
public class VehicleModels {

    @Id
    @Column(name = "id_vehicle_model")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "cant_place")
    private Integer cantPlace;

    @Column(name = "is_automatic")
    private Boolean isAutomatic;

    @ManyToOne
    @JoinColumn(name = "id_brand", referencedColumnName = "id_brand")
    @JsonIgnore
    private Brand brand;
}