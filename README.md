# poc-1
Proof of concept project

## Tools
- Java 21
- Gradle 8.6
- Spring Boot 3.2.2
- RabbitMQ 3.12
- MongoDB 6.0.13

## Run
```console
docker pull rabbitmq:3.12-management
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

http://localhost:8080/send-message/Hello, World!
```
