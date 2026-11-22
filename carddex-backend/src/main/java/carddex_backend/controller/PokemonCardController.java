package carddex_backend.controller;
import carddex_backend.dto.PokemonCardDTO;
import carddex_backend.dto.PokemonCardResponseDTO;
import carddex_backend.model.PokemonCard;
import carddex_backend.service.PokemonCardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<PokemonCardResponseDTO> listarTodas() {
        List<PokemonCardResponseDTO> cards = service.listarTodas().stream().map(card -> service.converterParaDTO(card)).toList();
        return cards;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonCardResponseDTO cadastrar(@Valid @RequestBody PokemonCardDTO carta) {
        PokemonCard card =  service.cadastrar(carta);
        return service.converterParaDTO(card);
    }

    @GetMapping("/{id}")
    public PokemonCardResponseDTO buscarPorId(@PathVariable Long id) {
        PokemonCard card = service.buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta não encontrada"));
        return service.converterParaDTO(card);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonCardResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PokemonCardDTO dadosAtualizados) {
        PokemonCard card = service.atualizar(id, dadosAtualizados);
        PokemonCardResponseDTO dto = service.converterParaDTO(card);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<PokemonCardResponseDTO> buscarPorNome(@RequestParam String name) {
        List<PokemonCardResponseDTO> cards = service.buscarPorNome(name).stream().map(card -> service.converterParaDTO(card)).toList();
        return cards;
    }

    @GetMapping("/type/{type}")
    public List<PokemonCardResponseDTO> buscarPorTipo(@PathVariable String type) {
        List<PokemonCardResponseDTO> cards = service.buscarPorTipo(type).stream().map(card -> service.converterParaDTO(card)).toList();
        return cards;
    }

    @GetMapping("/rarity/{rarity}")
    public List<PokemonCardResponseDTO> buscarPorRaridade(@PathVariable String rarity) {
        List<PokemonCardResponseDTO> cards = service.buscarPorRaridade(rarity).stream().map(card -> service.converterParaDTO(card)).toList();
        return cards;
    }
    @GetMapping("/filter")
    public List<PokemonCardResponseDTO> buscarPorTipoERaridade(@RequestParam String type, @RequestParam String rarity) {
        List<PokemonCardResponseDTO> cards = service.buscarPorTipoERaridade(type,rarity).stream().map(card -> service.converterParaDTO(card)).toList();
        return cards;
    }
}