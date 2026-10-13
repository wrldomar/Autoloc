package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByClient_IdClient(Long clientId);

    List<Reservation> findByVehicule_IdVehicule(Long vehiculeId);

    List<Reservation> findByStatut(StatutReservation statut);

    /** Vrai si le véhicule a déjà une réservation (parmi les statuts donnés) qui chevauche la période. */
    @Query("""
            select count(r) > 0 from Reservation r
            where r.vehicule.idVehicule = :vehiculeId
              and r.statut in :statuts
              and r.dateDebut <= :fin
              and r.dateFin >= :debut
            """)
    boolean existsChevauchement(@Param("vehiculeId") Long vehiculeId,
                                @Param("debut") LocalDate debut,
                                @Param("fin") LocalDate fin,
                                @Param("statuts") Collection<StatutReservation> statuts);
}
