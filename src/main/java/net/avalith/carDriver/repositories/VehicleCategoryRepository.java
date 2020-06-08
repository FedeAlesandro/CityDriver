package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory,Long> {
    Optional<VehicleCategory> findByName(String name);
}
