package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContrat_IdContrat(Long contratId);

    List<Paiement> findByModePaiement(ModePaiement modePaiement);

    /** Total payé pour un contrat (null s'il n'y a aucun paiement). */
    @Query("select sum(p.montant) from Paiement p where p.contrat.idContrat = :contratId")
    BigDecimal totalPayeParContrat(@Param("contratId") Long contratId);
}
