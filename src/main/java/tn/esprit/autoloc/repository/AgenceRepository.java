package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

    List<Agence> findByVilleIgnoreCase(String ville);

    List<Agence> findByNomContainingIgnoreCase(String nom);
}
