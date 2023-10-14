package gr.opap.techbiz.cap.antifraud.service;

import gr.opap.techbiz.cap.antifraud.exception.IllegalPaymentException;
import org.springframework.stereotype.Component;

@Component
public class AntifraudService {

    public void checkPaymentType(String paymentType) throws IllegalPaymentException {
        if ("crypto".equalsIgnoreCase(paymentType)) {
            throw new IllegalPaymentException();
        }
    }
}
