package br.com.ifgoiano.fila_pub_sub.messaging.producer;

import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransporteProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void enviarParaTransporte(Pedido pedido) {
        streamBridge.send("enviarParaTransporte-out-0", MessageBuilder.withPayload(pedido).build());
    }
}
