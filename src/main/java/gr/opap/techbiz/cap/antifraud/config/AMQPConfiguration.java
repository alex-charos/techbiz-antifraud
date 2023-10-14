package gr.opap.techbiz.cap.antifraud.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

    @Bean
    public Declarables paymentConfig() {
        TopicExchange resultsPublished = ExchangeBuilder.topicExchange("antifraudResults").build();

        Queue paymentsQueue = QueueBuilder.durable("antifraudCheck").build();

        TopicExchange paymentsExchange = ExchangeBuilder.topicExchange("paymentCheckExchange").build();
        Binding paymentsBinding = BindingBuilder.bind(paymentsQueue).to(paymentsExchange).with("#");

        return new Declarables(resultsPublished, paymentsQueue, paymentsBinding);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
