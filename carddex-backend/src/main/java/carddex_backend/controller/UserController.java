package carddex_backend.controller;

import carddex_backend.dto.UserLoginDTO;
import carddex_backend.dto.UserRegisterDTO;
import carddex_backend.dto.UserResponseDTO;
import carddex_backend.model.PokemonCard;
import carddex_backend.model.User;
import carddex_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> cadastrar(@Valid @RequestBody UserRegisterDTO dados) {
        UserResponseDTO user =  service.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO dados) {
        UserResponseDTO user =  service.login(dados);
        return ResponseEntity.ok(user);
    }
}
