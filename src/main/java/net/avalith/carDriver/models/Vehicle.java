package net.avalith.carDriver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name="cant_place")
    private Integer cantPlace;

    @Column(columnDefinition = "boolean default true")
    private Boolean available = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Providers provider;

    @ManyToOne
    @JoinColumn(name = "category_vehicles_id")
    @JsonIgnore
    private Categories_vehicles category_vehicles;




}
