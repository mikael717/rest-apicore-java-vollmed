package med.voll.apicore.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleErorr400(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleErrorBadCredentials(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleErrorAuthentication(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failure");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleErrorAccessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    public ResponseEntity handleError500(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    private record DataValidationError(String field, String message){
        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
