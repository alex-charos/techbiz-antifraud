package gr.opap.techbiz.cap.antifraud.rest;

import gr.opap.techbiz.cap.antifraud.exception.IllegalPaymentException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/antifraud")
public class AntifraudRESTService {

    @PostMapping
    public void checkType(@RequestBody  PaymentDTO dto) throws IllegalPaymentException {
        if ("crypto".equalsIgnoreCase(dto.paymentType())) {
            throw new IllegalPaymentException();
        }
    }

    record PaymentDTO(String id, String paymentType, Integer amountInCents, String userId) {}
}
