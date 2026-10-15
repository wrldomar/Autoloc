package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {
    private final AgenceRepository agenceRepository;

    @Override
    public Agence create(Agence agence) {
        requireNew(agence.getIdAgence());
        requireText(agence.getNom(), "Le nom de l'agence est obligatoire");
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {
        Agence existing = findById(id);
        requireText(agence.getNom(), "Le nom de l'agence est obligatoire");
        existing.setNom(agence.getNom());
        existing.setAdresse(agence.getAdresse());
        existing.setVille(agence.getVille());
        existing.setTelephone(agence.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        requireExisting(id, agenceRepository.existsById(id), "Agence");
        agenceRepository.deleteById(id);
    }

    private static void requireNew(Long id) {
        if (id != null) {
            throw new IllegalArgumentException("Une nouvelle agence ne doit pas avoir d'identifiant");
        }
    }

    private static void requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void requireExisting(Long id, boolean exists, String resource) {
        if (!exists) {
            throw new ResourceNotFoundException(resource, id);
        }
    }
}
