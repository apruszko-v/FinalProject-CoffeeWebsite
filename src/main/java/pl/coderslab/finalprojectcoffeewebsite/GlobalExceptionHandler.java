package pl.coderslab.finalprojectcoffeewebsite;

import jakarta.persistence.NonUniqueResultException;
import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    //    username/email taken
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//
//    //    sth not found
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    //    general error
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Unexpected error: " + e.getMessage());
//    }
//}

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Obsługa błędów walidacji @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors); // 400 Bad Request
    }

    // Obsługa błędów biznesowych (np. zła logika aplikacji)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(error); // 400 Bad Request
    }

    // Obsługa sytuacji "not found"
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404 Not Found
    }

    // Obsługa nieprzewidzianych błędów
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Unexpected error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // 500 Internal Server Error
    }

    @ExceptionHandler({NonUniqueResultException.class, IncorrectResultSizeDataAccessException.class})
    public ResponseEntity<Map<String, String>> handleNonUniqueResult(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "You can only enter one coffee.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
