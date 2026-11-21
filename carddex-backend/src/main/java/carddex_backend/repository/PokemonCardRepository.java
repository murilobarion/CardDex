package carddex_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import carddex_backend.model.PokemonCard;

public interface PokemonCardRepository extends JpaRepository<PokemonCard, Long> {

}