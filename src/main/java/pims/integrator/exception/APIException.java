package pims.integrator.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public APIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
