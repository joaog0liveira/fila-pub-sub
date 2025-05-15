package br.com.ifgoiano.fila_pub_sub.controller;

import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EventoProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void publicarEventoPedidoProcessado(Pedido pedido) {
        streamBridge.send("eventoPedidoProcessado-out-0", MessageBuilder.withPayload(pedido).build());
    }
}