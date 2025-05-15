package br.com.ifgoiano.fila_pub_sub.controller;

import br.com.ifgoiano.fila_pub_sub.messaging.PedidoProducer;
import br.com.ifgoiano.fila_pub_sub.model.Pedido;
import br.com.ifgoiano.fila_pub_sub.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoProducer pedidoProducer;

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido) {
        pedido.setId(UUID.randomUUID().toString());
        pedido.setStatus(StatusPedido.CRIADO);
        pedidoProducer.enviarParaFilaProcessamento(pedido);
        return "Pedido enviado para processamento. ID: " + pedido.getId();
    }
}
