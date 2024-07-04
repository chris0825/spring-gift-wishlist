package gift.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProductNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleInvalidProductNameException(InvalidProductNameException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
    }

}