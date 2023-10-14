package gr.opap.techbiz.cap.antifraud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Payment Type is Illegal!")
public class IllegalPaymentException extends Exception {
}
