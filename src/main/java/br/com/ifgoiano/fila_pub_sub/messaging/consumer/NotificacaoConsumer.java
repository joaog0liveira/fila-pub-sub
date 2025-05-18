package br.com.ifgoiano.fila_pub_sub.messaging.consumer;

import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class NotificacaoConsumer {

    @Bean
    public Consumer<Pedido> notificarPedidoProcessado() {
        return pedido -> {
            System.out.println("[Notificação] Seu pedido " + pedido.getId() + " foi processado!");
        };
    }

    @Bean
    public Consumer<Pedido> notificarPedidoEntregue() {
        return pedido -> {
            System.out.println("[Notificação] Seu pedido " + pedido.getId() + " foi entregue!");
        };
    }
}
