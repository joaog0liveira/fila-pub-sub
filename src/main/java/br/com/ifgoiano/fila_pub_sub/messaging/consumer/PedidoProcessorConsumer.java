package br.com.ifgoiano.fila_pub_sub.messaging.consumer;

import br.com.ifgoiano.fila_pub_sub.messaging.producer.EventoProducer;
import br.com.ifgoiano.fila_pub_sub.messaging.producer.TransporteProducer;
import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import br.com.ifgoiano.fila_pub_sub.model.StatusPedido;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class PedidoProcessorConsumer {

    private final TransporteProducer transporteProducer;
    private final EventoProducer eventoProducer;

    public PedidoProcessorConsumer(TransporteProducer transporteProducer, EventoProducer eventoProducer) {
        this.transporteProducer = transporteProducer;
        this.eventoProducer = eventoProducer;
    }

    @Bean
    public Consumer<Pedido> processarPedido() {
        return pedido -> {
            System.out.println("Processando pedido: " + pedido.getId());

            pedido.setStatus(StatusPedido.PROCESSADO);

            transporteProducer.enviarParaTransporte(pedido);
            eventoProducer.publicarEventoPedidoProcessado(pedido);

            System.out.println("Pedido processado e enviado para transporte: " + pedido.getId());
        };
    }
}
