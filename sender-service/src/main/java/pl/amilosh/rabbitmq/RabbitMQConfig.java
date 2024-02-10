package pl.amilosh.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String HELLO_EXCHANGE = "hello_exchange";
    public static final String HELLO_EXCHANGE_RK = "hello_exchange_rk";
    public static final String SECOND_EXCHANGE_RK = "second_exchange_rk";
    public static final String HELLO_QUEUE = "hello_queue";
    public static final String SECOND_QUEUE = "second_queue";

    @Bean
    Queue helloQueue() {
        return new Queue(HELLO_QUEUE);
    }

    @Bean
    Queue secondQueue() {
        return new Queue(SECOND_QUEUE);
    }

    @Bean
    FanoutExchange helloExchange() {
        return new FanoutExchange(HELLO_EXCHANGE);
    }

    @Bean
    Binding helloBinding(Queue helloQueue, FanoutExchange helloExchange) {
        return BindingBuilder.bind(helloQueue).to(helloExchange);
    }

    @Bean
    Binding secondBinding(Queue secondQueue, FanoutExchange helloExchange) {
        return BindingBuilder.bind(secondQueue).to(helloExchange);
    }
}
