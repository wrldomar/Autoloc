package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    Optional<Contrat> findByClient_IdClient(Long clientId);

    Optional<Contrat> findByReservation_IdReservation(Long reservationId);

    List<Contrat> findByValide(boolean valide);
}
