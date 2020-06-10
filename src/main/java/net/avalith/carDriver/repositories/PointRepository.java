package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Optional<Point> findByCoordinateLatitudeAndCoordinateLongitude(String coordinateLatitude, String coordinateLongitude);

    @Query(value = "select * from points where is_active = true ", nativeQuery = true)
    List<Point> getAll();

    @Modifying
    @Transactional
    @Query(value = "update points set is_active = false where coordinate_latitude = ?1 and coordinate_longitude = ?2 ", nativeQuery = true)
    Integer delete(String coordinateLatitude, String coordinateLongitude);
}
