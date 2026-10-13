package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {

    Optional<Assurance> findByVehicule_IdVehicule(Long vehiculeId);

    Optional<Assurance> findByNumeroContrat(String numeroContrat);

    List<Assurance> findByDateExpirationBefore(LocalDate date);
}
