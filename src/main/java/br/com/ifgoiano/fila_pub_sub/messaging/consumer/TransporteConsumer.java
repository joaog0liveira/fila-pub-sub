package br.com.ifgoiano.fila_pub_sub.messaging.consumer;

import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import br.com.ifgoiano.fila_pub_sub.model.StatusPedido;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class TransporteConsumer {

    private final EventoEntregaProducer eventoEntregaProducer;

    public TransporteConsumer(EventoEntregaProducer eventoEntregaProducer) {
        this.eventoEntregaProducer = eventoEntregaProducer;
    }

    @Bean
    public Consumer<Pedido> transportarPedido() {
        return pedido -> {
            System.out.println("Transportando pedido: " + pedido.getId());

            pedido.setStatus(StatusPedido.ENTREGUE);

            eventoEntregaProducer.publicarEventoPedidoEntregue(pedido);

            System.out.println("Pedido entregue: " + pedido.getId());
        };
    }
}
