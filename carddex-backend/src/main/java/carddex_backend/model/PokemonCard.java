package carddex_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table (name = "pokemon_cards")
public class PokemonCard {
    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da carta é obrigatório")
    private String name;

    @NotBlank(message = "A coleção da carta é obrigatório")
    private String setName;

    @NotBlank(message = "O tipo da carta é obrigatório")
    private String type;

    @NotBlank(message = "A raridade da carta é obrigatório")
    private String rarity;

    private String imageUrl;

    protected PokemonCard() {
    }

    public PokemonCard(String name, String type, String rarity, String setName, String imageUrl) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.setName = setName;
        this.imageUrl = imageUrl;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}