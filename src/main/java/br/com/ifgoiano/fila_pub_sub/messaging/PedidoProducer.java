package br.com.ifgoiano.fila_pub_sub.messaging;

import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void enviarParaFilaProcessamento(Pedido pedido) {
        Message<Pedido> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("enviarPedido-out-0", message);
    }
}
