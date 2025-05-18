package br.com.ifgoiano.fila_pub_sub.messaging.consumer;


import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EventoEntregaProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void publicarEventoPedidoEntregue(Pedido pedido) {
        streamBridge.send("eventoPedidoEntregue-out-0", MessageBuilder.withPayload(pedido).build());
    }
}
