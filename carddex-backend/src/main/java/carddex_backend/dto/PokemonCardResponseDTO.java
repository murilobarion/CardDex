package carddex_backend.dto;

public record PokemonCardResponseDTO(
        Long id,
        String name,
        String type,
        String rarity,
        String setName,
        String imageUrl
) {
}
