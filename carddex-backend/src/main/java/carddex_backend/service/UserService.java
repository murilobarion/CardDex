package carddex_backend.service;

import carddex_backend.dto.PokemonCardResponseDTO;
import carddex_backend.dto.UserLoginDTO;
import carddex_backend.dto.UserRegisterDTO;
import carddex_backend.dto.UserResponseDTO;
import carddex_backend.model.User;
import carddex_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO cadastrar(UserRegisterDTO dados) {
        if (repository.existsByEmailIgnoreCase(dados.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        String hash = passwordEncoder.encode(dados.password());
        User user = new User(dados.name(), dados.email(), hash);
        User salvo = repository.save(user);
        return new UserResponseDTO(salvo.getId(), salvo.getName(), salvo.getEmail());
    }

    public UserResponseDTO login(UserLoginDTO dados) {
        User user = repository.findByEmailIgnoreCase(dados.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos"));
        if (!passwordEncoder.matches(dados.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválido");
        }

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}
