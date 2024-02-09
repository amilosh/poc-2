package pl.amilosh.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String HELLO_EXCHANGE = "hello_exchange";
    public static final String HELLO_EXCHANGE_RK = "hello_exchange_rk";
    public static final String HELLO_QUEUE = "hello_queue";

    @Bean
    Queue helloQueue() {
        return new Queue(HELLO_QUEUE);
    }

    @Bean
    TopicExchange helloExchange() {
        return new TopicExchange(HELLO_EXCHANGE);
    }

    @Bean
    Binding bindingHello(Queue helloQueue, TopicExchange helloExchange) {
        return BindingBuilder.bind(helloQueue).to(helloExchange).with(HELLO_EXCHANGE_RK);
    }
}
