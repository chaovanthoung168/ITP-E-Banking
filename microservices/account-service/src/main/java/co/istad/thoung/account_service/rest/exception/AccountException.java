package co.istad.thoung.account_service.rest.exception;

import co.istad.thoung.account_service.domain.exception.AccountDomainException;
import co.istad.thoung.common.domain.application.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class AccountException {

//    // ✅ catches interceptor exception and returns 404 instead of 500
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
//        ProblemDetail problem = ProblemDetail
//                .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        problem.setTitle("Customer Not Found");
//        problem.setProperty("timestamp", Instant.now());
//        return problem;
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ProblemDetail handleRuntime(RuntimeException ex) {
//        ProblemDetail problem = ProblemDetail
//                .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//        problem.setTitle("Internal Server Error");
//        problem.setProperty("timestamp", Instant.now());
//        return problem;
//    }

    @ExceptionHandler(AccountDomainException.class)
    public ErrorResponse handleAccountDomainException(AccountDomainException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
    }
}
