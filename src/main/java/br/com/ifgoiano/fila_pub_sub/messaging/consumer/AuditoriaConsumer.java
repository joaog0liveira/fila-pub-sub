package br.com.ifgoiano.fila_pub_sub.messaging.consumer;

import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AuditoriaConsumer {

    @Bean
    public Consumer<Pedido> auditarPedidoProcessado() {
        return pedido -> {
            System.out.println("[Auditoria] Pedido processado: " + pedido);
        };
    }

    @Bean
    public Consumer<Pedido> auditarPedidoEntregue() {
        return pedido -> {
            System.out.println("[Auditoria] Pedido entregue: " + pedido);
        };
    }
}
