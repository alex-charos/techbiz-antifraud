package gr.opap.techbiz.cap.antifraud.listener;

import gr.opap.techbiz.cap.antifraud.exception.IllegalPaymentException;
import gr.opap.techbiz.cap.antifraud.service.AntifraudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentListener.class);

    private final AntifraudService antifraudService;
    private final RabbitTemplate rabbitTemplate;

    public PaymentListener(AntifraudService antifraudService, RabbitTemplate rabbitTemplate) {
        this.antifraudService = antifraudService;
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = {"antifraudCheck"})
    public void checkPayment(PaymentRequestDTO paymentRequestDTO) {
        try {
            antifraudService.checkPaymentType(paymentRequestDTO.paymentType());
            LOGGER.info("Payment checked successfully: {}", paymentRequestDTO);
            rabbitTemplate.convertAndSend("antifraudResults", "success", paymentRequestDTO);
        } catch (IllegalPaymentException ipe) {
            LOGGER.info("Payment check failed: {}", paymentRequestDTO);
            rabbitTemplate.convertAndSend("antifraudResults", "failure", paymentRequestDTO);
        }

    }

    public record PaymentRequestDTO(String paymentType, Integer amountInCents, String userId){

    }
}
