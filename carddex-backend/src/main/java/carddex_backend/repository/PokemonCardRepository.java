package carddex_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import carddex_backend.model.PokemonCard;

import java.util.List;

public interface PokemonCardRepository extends JpaRepository<PokemonCard, Long> {

    List<PokemonCard> findByNameContainingIgnoreCase(String name);
    List<PokemonCard> findByTypeIgnoreCase(String type);
    List<PokemonCard> findByRarityIgnoreCase(String rarity);
    List<PokemonCard> findByTypeIgnoreCaseAndRarityIgnoreCase(String type, String rarity);
}