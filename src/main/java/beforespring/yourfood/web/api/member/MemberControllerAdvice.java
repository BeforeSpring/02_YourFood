package beforespring.yourfood.web.api.member;

import beforespring.yourfood.auth.authmember.service.exception.AuthMemberNotFoundException;
import beforespring.yourfood.auth.authmember.service.exception.ConfirmNotFoundException;
import beforespring.yourfood.web.api.common.ErrorResponse;
import beforespring.yourfood.web.api.common.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MemberControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthMemberNotFoundException.class)
    public ErrorResponse<String> handleMemberNotFoundException(AuthMemberNotFoundException e, HttpServletRequest request) {
        return new ErrorResponse<>(StatusCode.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConfirmNotFoundException.class)
    public ErrorResponse<String> handleConfirmNotFoundException(ConfirmNotFoundException e, HttpServletRequest request) {
        return new ErrorResponse<>(StatusCode.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse<String> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        return new ErrorResponse<>(StatusCode.BAD_REQUEST, e.getMessage(), request.getRequestURI());
    }


    }
