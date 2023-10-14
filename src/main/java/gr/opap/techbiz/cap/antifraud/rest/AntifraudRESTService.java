package gr.opap.techbiz.cap.antifraud.rest;

import gr.opap.techbiz.cap.antifraud.exception.IllegalPaymentException;
import gr.opap.techbiz.cap.antifraud.service.AntifraudService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/antifraud")
public class AntifraudRESTService {
    private final AntifraudService antifraudService;

    public AntifraudRESTService(AntifraudService antifraudService) {
        this.antifraudService = antifraudService;
    }

    @PostMapping
    public void checkType(@RequestBody  PaymentDTO dto) throws IllegalPaymentException {
        antifraudService.checkPaymentType(dto.paymentType());
    }

    record PaymentDTO(String id, String paymentType, Integer amountInCents, String userId) {}
}
