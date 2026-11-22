package carddex_backend.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.postgresql.util.PSQLException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarErro(ResponseStatusException exception) {
        Map<String, Object> body = Map.of("status", exception.getStatusCode().value(), "message", exception.getReason());
        return ResponseEntity.status(exception.getStatusCode()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException exception) {
        String mensagem = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        Map<String, Object> body = Map.of("status", 400, "message", mensagem);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> tratarIntegridade(DataIntegrityViolationException exception) {
        Throwable causa = exception;

        while (causa != null) {
            System.out.println(causa.getClass().getSimpleName());
            if (causa instanceof PSQLException erroPostgres) {
                if ("23505".equals(erroPostgres.getSQLState())) {
                    if (erroPostgres.getServerErrorMessage() != null) {
                        String restricao = erroPostgres.getServerErrorMessage().getConstraint();
                        if ("idx_users_email_unique".equals(restricao)  || "uk6doktott2kjsp8vw4d0m25fb7".equals(restricao)) {
                            Map<String, Object> body = Map.of("status", 409, "message", "E-mail já cadastrado");
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
                        }
                    }
                }
            }
            causa = causa.getCause();
        }

        throw exception;
    }
}