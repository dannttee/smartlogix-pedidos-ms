package com.smartlogix.pedidos.messaging;

import com.smartlogix.pedidos.config.RabbitMQConfig;
import com.smartlogix.pedidos.event.PedidoCreadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PedidoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarPedidoCreado(PedidoCreadoEvent event) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE,
            RabbitMQConfig.ROUTING_KEY,
            event
        );
        System.out.println("[RabbitMQ] ✅ Evento publicado → pedido #" + event.getPedidoId());
    }
}