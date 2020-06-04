package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Providers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Providers, Long> {
    Optional<Providers> findById(Long id);
    Providers findByName(String name);
}
