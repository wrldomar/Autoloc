package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "assurance")
@Getter
@Setter
@NoArgsConstructor
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssurance;

    @Column(nullable = false, length = 50)
    private String numeroContrat;

    @Column(nullable = false)
    private LocalDate dateExpiration;

    @OneToOne
    @JoinColumn(name = "vehicule_id", nullable = false, unique = true)
    private Vehicule vehicule;
}