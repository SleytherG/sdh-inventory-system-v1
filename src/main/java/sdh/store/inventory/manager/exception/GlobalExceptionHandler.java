package sdh.store.inventory.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build(), HttpStatus.UNAUTHORIZED);
    }

}
