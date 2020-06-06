package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Optional<Point> findByCoordinateLatitudeAndCoordinateLongitude(String coordinateLatitude, String coordinateLongitude);
}
