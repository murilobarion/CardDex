package carddex_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import carddex_backend.model.PokemonCard;
import carddex_backend.repository.PokemonCardRepository;

@Service
public class PokemonCardService {
    private final PokemonCardRepository repository;

    public PokemonCardService(PokemonCardRepository repository) {
        this.repository = repository;
    }

    public List<PokemonCard> listarTodas() {
        return repository.findAll();
    }

    public PokemonCard cadastrar(PokemonCard card) {
        return repository.save(card);
    }
}