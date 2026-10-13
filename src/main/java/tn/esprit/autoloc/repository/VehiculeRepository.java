package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    Optional<Vehicule> findByImmatriculation(String immatriculation);

    boolean existsByImmatriculation(String immatriculation);

    List<Vehicule> findByStatut(StatutVehicule statut);

    List<Vehicule> findByCategorie(CategorieVehicule categorie);

    List<Vehicule> findByAgence_IdAgence(Long agenceId);

    List<Vehicule> findByStatutAndAgence_IdAgence(StatutVehicule statut, Long agenceId);

    /** Véhicules (hors maintenance) sans réservation active qui chevauche la période. */
    @Query("""
            select v from Vehicule v
            where v.statut <> tn.esprit.autoloc.domain.StatutVehicule.MAINTENANCE
              and not exists (
                  select r.idReservation from Reservation r
                  where r.vehicule = v
                    and r.statut in :statuts
                    and r.dateDebut <= :fin
                    and r.dateFin >= :debut)
            """)
    List<Vehicule> findDisponibles(@Param("debut") LocalDate debut,
                                   @Param("fin") LocalDate fin,
                                   @Param("statuts") Collection<StatutReservation> statuts);
}
