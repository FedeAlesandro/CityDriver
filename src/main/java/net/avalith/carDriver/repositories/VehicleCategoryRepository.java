package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory,Long> {
    Optional<VehicleCategory> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "update vehicle_categories set is_active = false where name = ?1 ", nativeQuery = true)
    Integer delete(String name);
}
