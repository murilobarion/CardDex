package carddex_backend.dto;

import jakarta.validation.constraints.NotBlank;

public record PokemonCardDTO(
        @NotBlank(message = "O nome da carta é obrigatório")
        String name,

        @NotBlank(message = "O tipo da carta é obrigatório")
        String type,

        @NotBlank(message = "A raridade da carta é obrigatório")
        String rarity,

        @NotBlank(message = "A coleção da carta é obrigatório")
        String setName,


        String imageUrl ) {
}
