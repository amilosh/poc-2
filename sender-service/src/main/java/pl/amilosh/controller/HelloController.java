package pl.amilosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static pl.amilosh.rabbitmq.RabbitMQConfig.HELLO_EXCHANGE;
import static pl.amilosh.rabbitmq.RabbitMQConfig.HELLO_EXCHANGE_RK;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping(value = "/send-message/{message}")
    public String sendMessage(@PathVariable(name = "message") String message) {
        String response;
        try {
            System.out.println("Send message to RabbitMQ");
            rabbitTemplate.convertAndSend(HELLO_EXCHANGE, HELLO_EXCHANGE_RK, message);
            response = "Message '" + message + "' is successfully sent to RabbitMQ";
        } catch (Exception e) {
            System.out.println("RabbitMQ exception: " + e.getMessage());
            e.printStackTrace();
            response = "Exception during sending to RabbitMQ: " + e.getMessage();
        }
        return response;
    }
}
