package carddex_backend.controller;
import carddex_backend.model.PokemonCard;
import carddex_backend.service.PokemonCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class PokemonCardController {

    private final PokemonCardService service;

    public PokemonCardController(PokemonCardService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonCard> listarTodas() {
        return service.listarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonCard cadastrar(@RequestBody PokemonCard card) {
        return service.cadastrar(card);
    }
}