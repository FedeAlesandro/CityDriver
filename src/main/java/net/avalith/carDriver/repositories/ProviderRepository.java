package net.avalith.carDriver.repositories;

import net.avalith.carDriver.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findById(Long id);
    Optional<Provider> findByName(String name);
    @Modifying
    @Transactional
    @Query(value = "update providers set is_active = false where name = ?1 and is_active = true ", nativeQuery = true)
    Integer delete(String name);
}
