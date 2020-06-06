package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.VehicleModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModels,Long> {
    Optional<VehicleModels> findByName(String name);
}
