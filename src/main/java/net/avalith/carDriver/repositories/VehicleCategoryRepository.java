package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.CategoryVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<CategoryVehicles,Long> {
    Optional<CategoryVehicles> findByName(String name);
}
