package br.com.hpaiva.campaignservice.queue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class AMQPProducer {

    private RabbitTemplate rabbitTemplate;

    RabbitMQPProperties rabbitMQProperties;

    public void sendMessage(Notification msg) {
        log.info("m=sendMessage message=" + msg.toString());
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), rabbitMQProperties.getRoutingKey(), msg);
    }

}
