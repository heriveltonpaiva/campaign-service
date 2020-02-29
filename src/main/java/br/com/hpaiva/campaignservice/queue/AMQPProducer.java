package br.com.hpaiva.campaignservice.queue;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AMQPProducer {

    private RabbitTemplate rabbitTemplate;

    RabbitMQPProperties rabbitMQProperties;

    public void sendMessage(Notification msg) {
        System.out.println("Send msg = " + msg.toString());
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), rabbitMQProperties.getRoutingKey(), msg);
    }

}
