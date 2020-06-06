package net.avalith.carDriver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.enums.Colors;
import net.avalith.carDriver.models.DTOS.Request.VehicleDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String domain;

    private Boolean available;

    @Enumerated(EnumType.STRING)
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Provider provider;
  
    @ManyToOne
    @JoinColumn(name = "vehicle_model_id")
    @JsonIgnore
    private VehicleModels vehicleModels;

    @ManyToOne
    @JoinColumn(name = "vehicle_model_id")
    @JsonIgnore
    private CategoryVehicles category_vehicles;
    
    public static Vehicle vehicleFromVehicleDTO(VehicleDTO vehicleDTO, Provider provider, VehicleModels vehicleModels, CategoryVehicles category_vehicles){
        Vehicle vehicle = new Vehicle();
        vehicle.setDomain(vehicleDTO.getDomain());
        vehicle.setAvailable(vehicleDTO.getAvailable());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setProvider(provider);
        vehicle.setCategory_vehicles(category_vehicles);
        vehicle.setVehicleModels(vehicleModels);
        return vehicle;
    }
}
