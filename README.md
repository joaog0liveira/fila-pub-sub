
# Sistema Distribuído de Pedidos com Filas e Eventos de Processamento e Entrega

Este projeto implementa um sistema distribuído para simular o ciclo de vida de um pedido em um e-commerce. Ele utiliza Spring Boot para a construção dos serviços, RabbitMQ como message broker para o gerenciamento de filas e Spring Cloud Stream para facilitar a comunicação baseada em eventos entre os diferentes componentes do sistema.

O objetivo é demonstrar o processamento de pedidos através de filas e a notificação de eventos para múltiplos serviços em cada etapa crucial do processo.

---

## Objetivo

Desenvolver um sistema distribuído robusto e escalável que simule:

- **Fila de Processamento de Pedidos**: processamento assíncrono.
- **Fila de Transporte/Entrega**: fila específica para transporte.
- **Eventos de Notificação**: padrão publish/subscribe para serviços como Notificação e Auditoria.

---

## Cenário do Sistema

### Fluxo:

1. **Criação do Pedido**: via REST.
2. **Fila de Processamento**: `enviarParaFilaProcessamento` no RabbitMQ.
3. **Processamento do Pedido**:
   - Serviço consumidor monitora a fila.
   - Processa e publica evento `pedido-processado`.
4. **Fila de Transporte**: `enviarParaTransporte`.
5. **Transporte e Entrega**:
   - Serviço consumidor entrega e publica `pedido-entregue`.
6. **Consumo de Eventos (Pub/Sub)**:
   - Serviços de Notificação e Auditoria consomem `pedido-processado` e `pedido-entregue`.

---

## Tecnologias Utilizadas

- Java (JDK 21)
- Spring Boot
- Spring Cloud Stream
- RabbitMQ
- Maven
- RESTful APIs

---

## Configuração e Execução

### Pré-requisitos

- **Java JDK 21**.
- **Apache Maven**.
- **RabbitMQ**:
  - O projeto está configurado para usar a **CloudAMQP** (RabbitMQ hospedado).
  - Alternativamente, você pode usar uma instância local (ex: via Docker).

---

### Acesso à Interface de Gerenciamento do RabbitMQ

- **Usando CloudAMQP (default do projeto):**
  - Acesse a URL do painel fornecida pela CloudAMQP.
  - No caso precisaria do usuário pessoal (do João Gabriel) para qualquer alteração que queria fazer na interface do CloudAMQP, porém o projeto já está todo configurado
  para rodar em qualquer máquina de acordo com o que foi pedido no projeto.

---

### Criação de Filas e Exchanges

- O **Spring Cloud Stream** cria automaticamente as filas e exchanges com base nas configurações de bindings do `application.yml`.

- Verifique nos logs da aplicação se os seguintes recursos foram criados:
  - `fila-processamento-pedidos`
  - `fila-transporte-pedidos`
  - Exchanges:
    - `evento-pedido-processado`
    - `evento-pedido-entregue`

- Se necessário, você pode:
  - Criar manualmente via interface do RabbitMQ.
  - Ajustar os bindings no `application.yml` para corrigir nomes ou roteamentos.

---

### Execução do Projeto

# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

> A aplicação será iniciada na porta `8080`. Verifique os logs para confirmar a criação das filas e o status da conexão com o RabbitMQ.

---

### Testando o Sistema

#### 1. Enviar um Pedido

POST: `http://localhost:8080/pedidos`  
JSON de exemplo:

```json
{
  "descricao": "Pedido de teste: 1x Livro, 2x Caneta"
}
```

#### 2. Observar o Fluxo

- Para monitoramento precisaria das credencias pessoais para acesso no CloudAMQP para observar o fluxo, porém acreditamos que pelo terminal dará pra ver direitinho.

---

## Equipe de Desenvolvimento

- João Gabriel de Oliveira Meireles  
- Darcson Lanúcio Ferreira Fernandes  
- Sabrina Ferreira Pires  

---

## Materiais de Referência

### Spring Cloud Stream

- [Documentação Oficial](https://spring.io/projects/spring-cloud-stream)
- [Simplificando o uso de Message Broker - Parte 1](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5)
- [Parte 2](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-parte-2-e82d02e1371c)
- [Baeldung - Introduction](https://www.baeldung.com/spring-cloud-stream)

### RabbitMQ

- [Site Oficial](https://www.rabbitmq.com/)
- [Tutoriais RabbitMQ](https://www.youtube.com/watch?v=SzcvuHjRJKE)

### Spring Boot & RabbitMQ

- [Spring Guides](https://spring.io/guides/gs/messaging-rabbitmq/)
- [Baeldung - Spring AMQP](https://www.baeldung.com/spring-amqp)
