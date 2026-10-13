package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 150)
    private String adresse;

    @Column(nullable = false, length = 80)
    private String ville;

    @Column(nullable = false, length = 30)
    private String telephone;

    @OneToMany(mappedBy = "agence")
    private Set<Vehicule> vehicules = new HashSet<>();

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employe> employes = new HashSet<>();
}