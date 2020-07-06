package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE rides SET state = 'IN_RIDE' WHERE id_ride = ?1 ;", nativeQuery = true)
    Ride startRide(Long id);
}
