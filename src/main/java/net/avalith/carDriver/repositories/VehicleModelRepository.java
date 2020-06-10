package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.VehicleModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModels,Long> {
    Optional<VehicleModels> findByName(String name);

    @Query(value = "select * from vehicle_models where is_active = true", nativeQuery = true)
    List<VehicleModels> getAllActive();

    @Modifying
    @Transactional
    @Query(value = "update vehicle_models set is_active = false where name = ?1 ", nativeQuery = true)
    Integer delete(String nameModel);
}
