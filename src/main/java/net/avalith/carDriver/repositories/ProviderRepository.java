package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findById(Long id);
    Provider findByName(String name);
}