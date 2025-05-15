package br.com.ifgoiano.fila_pub_sub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String id;
    private String descricao;
    private StatusPedido status;
}