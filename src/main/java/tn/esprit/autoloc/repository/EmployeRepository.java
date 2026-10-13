package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByAgence_IdAgence(Long agenceId);

    List<Employe> findByRole(RoleEmploye role);

    List<Employe> findByAgence_IdAgenceAndRole(Long agenceId, RoleEmploye role);
}
