package nextstep.subway.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgsException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

}
