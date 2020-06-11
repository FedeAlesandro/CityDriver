package net.avalith.carDriver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.enums.Colors;
import net.avalith.carDriver.models.dtos.requests.VehicleDtoRequest;

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
    @Column(name = "id_vehicle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String domain;

    private Boolean available;

    @Enumerated(EnumType.STRING)
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "id_provider", referencedColumnName = "id_provider")
    @JsonIgnore
    private Provider provider;
  
    @ManyToOne
    @JoinColumn(name = "id_vehicle_model", referencedColumnName = "id_vehicle_model")
    @JsonIgnore
    private VehicleModels vehicleModels;

    @ManyToOne
    @JoinColumn(name = "id_category_vehicle", referencedColumnName = "id_category_vehicle")
    @JsonIgnore
    private CategoryVehicles category_vehicles;
    
    public static Vehicle vehicleFromVehicleDTO(VehicleDtoRequest vehicleDtoRequest, Provider provider, VehicleModels vehicleModels, CategoryVehicles category_vehicles){
        Vehicle vehicle = new Vehicle();
        vehicle.setDomain(vehicleDtoRequest.getDomain());
        vehicle.setColor(vehicleDtoRequest.getColor());
        vehicle.setProvider(provider);
        vehicle.setCategory_vehicles(category_vehicles);
        vehicle.setVehicleModels(vehicleModels);
        return vehicle;
    }
}
