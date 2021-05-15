package kasei.springmvc.config.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "not implemented")
public class Error501Exception extends BizException {
    
    public Error501Exception(String message) {
        super(message);
    }

    public Error501Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Error501Exception(Throwable cause) {
        super(cause);
    }
}
