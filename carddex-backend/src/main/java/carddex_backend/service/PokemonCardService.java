package carddex_backend.service;

import java.util.List;
import java.util.Optional;

import carddex_backend.dto.PokemonCardDTO;
import carddex_backend.dto.PokemonCardResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import carddex_backend.model.PokemonCard;
import carddex_backend.repository.PokemonCardRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonCardService {
    private final PokemonCardRepository repository;

    public PokemonCardService(PokemonCardRepository repository) {
        this.repository = repository;
    }

    public List<PokemonCard> listarTodas() {
        return repository.findAll();
    }

    public PokemonCard cadastrar(@Valid PokemonCardDTO card) {
        PokemonCard carta = new PokemonCard(card.name(),card.type(), card.rarity(),card.setName(),card.imageUrl());
        return repository.save(carta);
    }

    public Optional<PokemonCard> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public PokemonCard atualizar(Long id, PokemonCardDTO dadosAtualizados) {
        PokemonCard card = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta não encontrada"));

        card.setName(dadosAtualizados.name());
        card.setSetName(dadosAtualizados.setName());
        card.setType(dadosAtualizados.type());
        card.setRarity(dadosAtualizados.rarity());
        card.setImageUrl(dadosAtualizados.imageUrl());

        return repository.save(card);
    }

    public void excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta não encontrada");
        }
    }

    public PokemonCardResponseDTO converterParaDTO(PokemonCard card) {

        return new PokemonCardResponseDTO(card.getId(), card.getName(), card.getType(),card.getRarity(),card.getSetName(),card.getImageUrl());
    }

    public List<PokemonCard> buscarPorNome(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<PokemonCard> buscarPorTipo(String type) {
        return repository.findByTypeIgnoreCase(type);
    }

    public List<PokemonCard> buscarPorRaridade(String rarity) {
        return repository.findByRarityIgnoreCase(rarity);
    }
    public List<PokemonCard> buscarPorTipoERaridade(String type, String rarity) {
        return repository.findByTypeIgnoreCaseAndRarityIgnoreCase(type, rarity);
    }
}