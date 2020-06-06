package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle>findByDomain(String domain);
}
